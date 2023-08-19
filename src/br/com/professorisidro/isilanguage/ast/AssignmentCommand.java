package br.com.professorisidro.isilanguage.ast;

public class AssignmentCommand extends AbstractCommand {
  private String id;
  private String expr;

  public AssignmentCommand(String id, String expr) {
    this.id = id;
    this.expr = expr;
  }

  @Override
  public String generateJavaCode(String baseTab) {
    return id + " = " + expr + ";";
  }

  @Override
  public String generateRubyCode() {
    return id + " = " + expr;
  }

  @Override
  public String toString() {
    return "[AssignmentCommand] id=" + id + ", expr=" + expr;
  }
}
