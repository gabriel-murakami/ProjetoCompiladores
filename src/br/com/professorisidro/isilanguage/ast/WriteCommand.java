package br.com.professorisidro.isilanguage.ast;

public class WriteCommand extends AbstractCommand {
	private String id;

	public WriteCommand(String id) {
		this.id = id;
	}

	@Override
	public String generateJavaCode(String baseTab) {
		return "System.out.println(" + id + ");";
	}

	@Override
	public String generateRubyCode() {
    return "puts " + id;
  }

	@Override
	public String toString() {
		return "[WriteCommand] id=" + id;
	}
}
