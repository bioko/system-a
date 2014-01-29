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

package org.biokoframework.systema.command;

import org.biokoframework.system.KILL_ME.commons.GenericFieldNames;
import org.biokoframework.system.command.Command;
import org.biokoframework.system.command.CommandException;
import org.biokoframework.system.context.Context;
import org.biokoframework.system.entity.binary.BinaryEntity;
import org.biokoframework.system.repository.core.SafeRepositoryHelper;
import org.biokoframework.systema.commons.SystemARepositories;
import org.biokoframework.systema.entity.dummyMultipart.DummyMultipart;

import java.util.Arrays;

import org.biokoframework.utils.fields.Fields;
import org.biokoframework.utils.repository.Repository;



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
		
		BinaryEntity firstFile = input.get(FIRST_FILE_PART_NAME);
		BinaryEntity secondFile = input.get(SECOND_FILE_PART_NAME);
		
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
