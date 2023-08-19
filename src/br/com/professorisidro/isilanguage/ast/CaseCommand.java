package br.com.professorisidro.isilanguage.ast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CaseCommand extends AbstractCommand {
  private String condition;
  private HashMap<String, ArrayList<AbstractCommand>> caseCommands;
  private ArrayList<AbstractCommand> defaultsCommands;
  private String tab = "  ";

  public CaseCommand(String condition, HashMap<String, ArrayList<AbstractCommand>> caseCommands,
      ArrayList<AbstractCommand> defaultsCommands) {
    this.condition = condition;
    this.caseCommands = caseCommands;
    this.defaultsCommands = defaultsCommands;
  }

  @Override
  public String generateJavaCode(String baseTab) {
    StringBuilder str = new StringBuilder();
    String currentTab = tab + baseTab;

    str.append("switch (" + condition + ") {\n");
    for (Map.Entry<String, ArrayList<AbstractCommand>> entry : caseCommands.entrySet()) {
      String caseItem = entry.getKey();
      ArrayList<AbstractCommand> commands = entry.getValue();
      str.append(currentTab + "case " + caseItem + ":\n");
      for (AbstractCommand cmd : commands) {
        str.append(tab + currentTab + cmd.generateJavaCode(baseTab) + "\n");
      }
      str.append(tab + currentTab + "break;\n");
    }
    if (!defaultsCommands.isEmpty()) {
      str.append(currentTab + "default:\n");
      for (AbstractCommand cmd : defaultsCommands) {
        str.append(tab + currentTab + cmd.generateJavaCode(baseTab) + "\n");
      }
      str.append(tab + currentTab + "break;\n");
    }
    str.append(baseTab + "}");

    return str.toString();
  }

  @Override
  public String generateRubyCode() {
    StringBuilder str = new StringBuilder();

    str.append("case " + condition + "\n");

    for (Map.Entry<String, ArrayList<AbstractCommand>> entry : caseCommands.entrySet()) {
      String caseItem = entry.getKey();
      ArrayList<AbstractCommand> commands = entry.getValue();
      str.append("when " + caseItem + "\n");
      for (AbstractCommand cmd : commands) {
        str.append("  " + cmd.generateRubyCode() + "\n");
      }
    }
    if (!defaultsCommands.isEmpty()) {
      str.append("else\n");
      for (AbstractCommand cmd : defaultsCommands) {
        str.append("  " + cmd.generateRubyCode() + "\n");
      }
    }
    str.append("end");

    return str.toString();
  }

  @Override
  public String toString() {
    return "[CaseCommand] caseCommands=" + caseCommands + ", condition=" + condition + ", defaultsCommands="
        + defaultsCommands;
  }
}
