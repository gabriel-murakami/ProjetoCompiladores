package br.com.professorisidro.isilanguage.ast;

import java.util.ArrayList;

public class WhileCommand extends AbstractCommand {
  private String condition;
  private ArrayList<AbstractCommand> commands;
  private String tab = "  ";

  public WhileCommand(String condition, ArrayList<AbstractCommand> commands) {
    this.condition = condition;
    this.commands = commands;
  }

  @Override
  public String generateJavaCode(String baseTab) {
    StringBuilder str = new StringBuilder();
    String currentTab = tab + baseTab;

    str.append("while (" + condition + ") {\n");
    for (AbstractCommand cmd : commands) {
      str.append(currentTab + cmd.generateJavaCode(baseTab) + "\n");
    }
    str.append(baseTab + "}");

    return str.toString();
  }

  @Override
  public String generateRubyCode() {
    StringBuilder str = new StringBuilder();

    str.append("while " + condition + "\n");

    for (AbstractCommand cmd : commands) {
      str.append("  " + cmd.generateRubyCode() + "\n");
    }

    str.append("end");
    return str.toString();
  }

  @Override
  public String toString() {
    return "[WhileCommand] commands=" + commands + ", condition=" + condition;
  }
}
