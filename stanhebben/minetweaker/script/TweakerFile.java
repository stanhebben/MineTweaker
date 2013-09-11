package stanhebben.minetweaker.script;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import stanhebben.minetweaker.api.TweakerException;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.script.statements.TweakerStatement;

public class TweakerFile {
	private ScriptEnvironment environment;
	private String file;
	private List<TweakerStatement> contents;
	
	public TweakerFile(ScriptEnvironment environment, String name, File file) throws IOException {
		this.environment = environment;
		this.file = name.startsWith("/") ? name : "/" + name;
		contents = loadScript(file);
	}
	
	public TweakerFile(ScriptEnvironment environment, String name, String contents) throws IOException {
		this.environment = environment;
		this.file = name.startsWith("/") ? name : "/" + name;
		this.contents = loadScript(contents);
	}
	
	public ScriptEnvironment getEnvironment() {
		return environment;
	}
	
	public String getName() {
		return file;
	}
	
	public TweakerFile findFile(String filename) {
		if (filename.startsWith("/")) return environment.getFile(filename);
		
		int slash1 = file.lastIndexOf('/');
		if (slash1 < 0) throw new TweakerExecuteException("filedir must be absolute");
		String dir = file.substring(0, slash1);
		
		while (filename.startsWith("../")) {
			filename = filename.substring(3);

			int slash = dir.lastIndexOf('/');
			if (slash < 0) throw new TweakerExecuteException("Cannot go up that many directories");
			dir = dir.substring(0, slash);
		}
		
		return environment.getFile(dir + '/' + filename);
	}
	
	public void execute(TweakerNameSpace global) throws TweakerException {
		for (TweakerStatement statement : contents) {
			if (statement.execute(global) != null) {
				throw new TweakerException(statement.getFile(), statement.getLine(), statement.getOffset(), "Cannot return a value in the main code");
			}
		}
	}
	
	private List<TweakerStatement> loadScript(File file) throws IOException {
		Reader reader = new BufferedReader(new FileReader(file));
		TweakerParser parser = new TweakerParser(this.file, reader);
		
		List<TweakerStatement> result = new ArrayList<TweakerStatement>();
		while (parser.hasNext()) {
			result.add(TweakerStatement.read(this, parser));
		}
		
		reader.close();
		return result;
	}
	
	private List<TweakerStatement> loadScript(String contents) throws IOException {
		TweakerParser parser = new TweakerParser(this.file, contents);
		
		List<TweakerStatement> result = new ArrayList<TweakerStatement>();
		while (parser.hasNext()) {
			result.add(TweakerStatement.read(this, parser));
		}
		return result;
	}
}
