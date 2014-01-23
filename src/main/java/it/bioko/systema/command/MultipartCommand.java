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
