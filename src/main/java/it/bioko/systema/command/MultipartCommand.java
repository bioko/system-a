/*
 * Copyright (c) 2014																 
 *	Mikol Faro			<mikol.faro@gmail.com>
 *	Simone Mangano		<simone.mangano@ieee.org>
 *	Mattia Tortorelli	<mattia.tortorelli@gmail.com>
 *
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * 
 */

package it.bioko.systema.command;

import it.bioko.system.KILL_ME.commons.GenericFieldNames;
import it.bioko.system.command.Command;
import it.bioko.system.command.CommandException;
import it.bioko.system.context.Context;
import it.bioko.system.entity.binary.BinaryEntity;
import it.bioko.system.repository.core.Repository;
import it.bioko.system.repository.core.SafeRepositoryHelper;
import it.bioko.systema.commons.SystemARepositories;
import it.bioko.systema.entity.dummyMultipart.DummyMultipart;
import it.bioko.utils.fields.Fields;

import java.util.Arrays;



public class MultipartCommand extends Command {

	public static final String FIRST_FILE_PART_NAME = "firstFile";
	public static final String SECOND_FILE_PART_NAME = "secondFile";

	public MultipartCommand() {		
	}
	
	public MultipartCommand(Context context) {
		_context = context;
	}
	
	
	@Override
	public Fields execute(Fields input) throws CommandException {
		logInput(input);
		
		Repository<BinaryEntity> blobRepo = _context.getRepository(SystemARepositories.BLOB_REPO_FOR_MULTIPART);
		Repository<DummyMultipart> dummyMPRepo = _context.getRepository(SystemARepositories.DUMMY_MULTIPART_REPO);
		
		BinaryEntity firstFile = (BinaryEntity) input.objectNamed(FIRST_FILE_PART_NAME);
		BinaryEntity secondFile = (BinaryEntity) input.objectNamed(SECOND_FILE_PART_NAME);
		
		DummyMultipart dummy = new DummyMultipart(input);
		
		// save blobs int its repo
		SafeRepositoryHelper.save(blobRepo, firstFile, _context);
		SafeRepositoryHelper.save(blobRepo, secondFile, _context);
		
		dummy.set(DummyMultipart.FIRST_FILE_ID, firstFile.getId());
		dummy.set(DummyMultipart.SECOND_FILE_ID, secondFile.getId());
		
		SafeRepositoryHelper.save(dummyMPRepo, dummy, _context);
		
		logOutput();
		return Fields.single(GenericFieldNames.RESPONSE, Arrays.asList(dummy));
	}

	@Override
	public String getName() {
		return this.getClass().getSimpleName();
	}

}
