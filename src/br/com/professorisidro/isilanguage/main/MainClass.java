package br.com.professorisidro.isilanguage.main;

import java.io.IOException;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import br.com.professorisidro.isilanguage.exceptions.SemanticException;
import br.com.professorisidro.isilanguage.parser.IsiLangLexer;
import br.com.professorisidro.isilanguage.parser.IsiLangParser;

/* esta é a classe que é responsável por criar a interação com o usuário
 * instanciando nosso parser e apontando para o arquivo fonte
 *
 * Arquivo fonte: extensao .isi
 *
 */
public class MainClass {
  public static void main(String[] args) {
    final String ANSI_RED = "\u001B[31m	";
    final String ANSI_RESET = "\u001B[0m";

    try {
      // Teste de tipos:
      // runTypes();
      // Teste de execução:
      runInput();

      System.out.println("Compilation Successful");
    } catch (SemanticException ex) {
      System.err.println(ANSI_RED + "SemanticError: " + ANSI_RESET + ex.getMessage());
    } catch (Exception ex) {
      ex.printStackTrace();
      System.err.println(ANSI_RED + "StandardError:" + ex.getMessage() + ANSI_RESET);
    }
  }

  public static void generateCodes(IsiLangParser parser) {
    parser.showCommands();
    parser.generateJavaCode();
    parser.generateRubyCode();
  }

  public static void runInput() throws IOException {
    IsiLangLexer lexer = initLexer("testeLinguagem.isi");
    // IsiLangLexer lexer = initLexer("testeSemLeia.isi");

    CommonTokenStream tokenStream = new CommonTokenStream(lexer);
    IsiLangParser parser = initParser(tokenStream);
    parser = new IsiLangParser(tokenStream);
    parser.prog();
    generateCodes(parser);
  }

  public static void runTypes() throws IOException {
    IsiLangLexer lexer = initLexer("testeTipos.isi");
    CommonTokenStream tokenStream = new CommonTokenStream(lexer);
    IsiLangParser parser = initParser(tokenStream);
    parser = new IsiLangParser(tokenStream);
    parser.prog();
  }

  public static IsiLangLexer initLexer(String fileName) throws IOException {
    IsiLangLexer lexer = new IsiLangLexer(CharStreams.fromFileName(fileName));
    return lexer;
  }

  public static IsiLangParser initParser(CommonTokenStream tokenStream) throws IOException {
    IsiLangParser parser = new IsiLangParser(tokenStream);
    return parser;
  }
}
