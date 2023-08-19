package br.com.professorisidro.isilanguage.ast;

import br.com.professorisidro.isilanguage.datastructures.IsiVariable;

public class ReadCommand extends AbstractCommand {

	private String id;
	private IsiVariable var;

	public ReadCommand(String id, IsiVariable var) {
		this.id = id;
		this.var = var;
	}

	private String getType(IsiVariable var) {
		if (var.getType() == IsiVariable.INT) {
			return ".nextInt();";
		} else if (var.getType() == IsiVariable.DOUBLE) {
			return ".nextDouble();";
		} else {
			return ".nextLine();";
		}
	}

	@Override
	public String generateJavaCode(String baseTab) {
		return id + " = scanner" + getType(var);
	}

  private String rubyGetType(IsiVariable var) {
    if (var.getType() == IsiVariable.INT) {
			return ".to_i";
		} else if (var.getType() == IsiVariable.DOUBLE) {
			return ".to_f";
		} else {
			return "";
		}
  }

	@Override
  public String generateRubyCode() {
    return id + " = gets.chomp" + rubyGetType(var);
  }

	@Override
	public String toString() {
		return "[ReadCommand] id=" + id;
	}
}
