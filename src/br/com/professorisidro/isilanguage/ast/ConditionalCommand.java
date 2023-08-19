package br.com.professorisidro.isilanguage.ast;

import java.util.ArrayList;

public class ConditionalCommand extends AbstractCommand {

	private String condition;
	private ArrayList<AbstractCommand> trueList;
	private ArrayList<AbstractCommand> falseList;
	private String tab = "	";

	public ConditionalCommand(String condition, ArrayList<AbstractCommand> trueList, ArrayList<AbstractCommand> falseList) {
		this.condition = condition;
		this.trueList = trueList;
		this.falseList = falseList;
	}

	@Override
	public String generateJavaCode(String baseTab) {
		StringBuilder str = new StringBuilder();
		String currentTab = tab + baseTab;

		str.append("if (" + condition + ") {\n");

		for (AbstractCommand cmd : trueList) {
			str.append(currentTab + cmd.generateJavaCode(currentTab) + "\n");
		}

		str.append(baseTab + "}");

		if (falseList.size() > 0) {
			str.append(" else {\n");

			for (AbstractCommand cmd : falseList) {
				str.append(currentTab + cmd.generateJavaCode(currentTab) + "\n");
			}

			str.append(baseTab + "}");
		}

		return str.toString();
	}

	@Override
	public String generateRubyCode() {
    StringBuilder str = new StringBuilder();

    str.append("if " + condition + "\n");

    for (AbstractCommand cmd : trueList) {
			str.append("  " + cmd.generateRubyCode() + "\n");
		}

    if (falseList.size() > 0) {
			str.append("else\n");
			for (AbstractCommand cmd : falseList) {
				str.append("  " + cmd.generateRubyCode() + "\n");
			}
		}

    str.append("end");
    return str.toString();
	}

	@Override
	public String toString() {
		return "[ConditionalCommand] condition=" + condition + ", trueList=" + trueList + ", falseList=" + falseList;
	}
}
