package br.com.professorisidro.isilanguage.ast;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import br.com.professorisidro.isilanguage.datastructures.IsiSymbol;
import br.com.professorisidro.isilanguage.datastructures.IsiSymbolTable;

public class IsiProgram {
	private IsiSymbolTable varTable;
	private ArrayList<AbstractCommand> comandos;
	private String programName;
  private String tab = "  ";
  private String doubleTab = tab + tab;

	public void generateJavaTarget() {
		StringBuilder str = new StringBuilder();

		if (hasReadCommand()) {
			str.append("import java.util.Scanner;\n");
    }

		str.append("public class MainClass {\n");
		str.append(tab + "public static void main(String args[]) {\n");

    if (hasReadCommand()) {
      str.append(doubleTab + "Scanner scanner = new Scanner(System.in);\n");
    }

		for (IsiSymbol symbol : varTable.getAll()) {
			str.append(doubleTab + symbol.generateJavaCode() + "\n");
		}
		for (AbstractCommand command : comandos) {
			str.append(doubleTab + command.generateJavaCode(doubleTab) + "\n");
		}

    if (hasReadCommand()) {
      str.append(doubleTab + "scanner.close();\n");
    }

		str.append(tab + "}\n");
		str.append("}\n");

		try {
			FileWriter fr = new FileWriter(new File("MainClass.java"));
			fr.write(str.toString());
			fr.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

  public boolean hasReadCommand() {
    for (AbstractCommand command : comandos) {
      if ("ReadCommand".equals(command.getClass().getSimpleName())) {
        return true;
      }
		}

    return false;
  }

	public void generateRubyTarget() {
    StringBuilder str = new StringBuilder();

		for (AbstractCommand cmd : comandos) {
			str.append(cmd.generateRubyCode() + "\n");
		}

    try {
			FileWriter fr = new FileWriter(new File("main.rb"));
			fr.write(str.toString());
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public IsiSymbolTable getVarTable() {
		return varTable;
	}

	public void setVarTable(IsiSymbolTable varTable) {
		this.varTable = varTable;
	}

	public ArrayList<AbstractCommand> getCommands() {
		return comandos;
	}

	public void setCommands(ArrayList<AbstractCommand> comandos) {
		this.comandos = comandos;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

}
