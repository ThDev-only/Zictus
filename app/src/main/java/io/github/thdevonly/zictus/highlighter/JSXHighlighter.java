package io.github.thdevonly.zictus.highlighter;

import android.content.Context;
import androidx.core.content.ContextCompat;

import com.amrdeveloper.codeview.CodeView;

import java.util.regex.Pattern;
import io.github.thdevonly.zictus.R;

public class JSXHighlighter {

    public static void applyJSXHighlight(Context context, CodeView codeView) {

        int keywordColor = ContextCompat.getColor(context, R.color.jsx_keyword);
        int stringColor = ContextCompat.getColor(context, R.color.jsx_string);
        int tagColor = ContextCompat.getColor(context, R.color.jsx_tag);
        int attrNameColor = ContextCompat.getColor(context, R.color.jsx_attr_name);
        int attrValueColor = ContextCompat.getColor(context, R.color.jsx_attr_value);
        int commentColor = ContextCompat.getColor(context, R.color.jsx_comment);
        int bracketColor = ContextCompat.getColor(context, R.color.jsx_bracket);
        int operatorColor = ContextCompat.getColor(context, R.color.jsx_operator);

        // Palavras-chave do JavaScript
        String[] jsKeywords = {
                "break", "case", "catch", "class", "const", "continue",
                "debugger", "default", "delete", "do", "else", "export",
                "extends", "finally", "for", "function", "if", "import",
                "in", "instanceof", "let", "new", "return", "super",
                "switch", "this", "throw", "try", "typeof", "var", "void",
                "while", "with", "yield", "async", "await"
        };

        for (String keyword : jsKeywords) {
            String regex = "\\b" + keyword + "\\b";
            codeView.addSyntaxPattern(Pattern.compile(regex), keywordColor);
        }

        // Palavras JSX comuns
        String[] jsxKeywords = {
                "className", "onClick", "onChange", "onSubmit", "style", "key", "ref"
        };

        for (String keyword : jsxKeywords) {
            String regex = "\\b" + keyword + "\\b";
            codeView.addSyntaxPattern(Pattern.compile(regex), attrNameColor);
        }

        // Tags HTML usadas no JSX
        String[] htmlTags = {
                "div", "span", "h1", "h2", "h3", "h4", "h5", "h6",
                "p", "a", "ul", "li", "img", "button", "input",
                "form", "label", "section", "article", "header", "footer",
                "nav", "main", "aside"
        };

        for (String tag : htmlTags) {
            String regex = "</?" + tag + "\\b";
            codeView.addSyntaxPattern(Pattern.compile(regex), tagColor);
        }

        // Strings: "..." ou '...'
        Pattern stringPattern = Pattern.compile("\"(.*?)\"|'(.*?)'");
        codeView.addSyntaxPattern(stringPattern, stringColor);

        // Números
        Pattern numberPattern = Pattern.compile("\\b\\d+(\\.\\d+)?\\b");
        codeView.addSyntaxPattern(numberPattern, attrValueColor);

        // Comentários de linha
        Pattern singleLineComment = Pattern.compile("//.*");
        codeView.addSyntaxPattern(singleLineComment, commentColor);

        // Comentários de bloco
        Pattern multiLineComment = Pattern.compile("/\\*(.|\\R)*?\\*/");
        codeView.addSyntaxPattern(multiLineComment, commentColor);

        // Operadores
        Pattern operatorPattern = Pattern.compile("[=<>!\\-+*/%]+");
        codeView.addSyntaxPattern(operatorPattern, operatorColor);

        // Parênteses e colchetes
        Pattern bracketPattern = Pattern.compile("[\\[\\](){}]");
        codeView.addSyntaxPattern(bracketPattern, bracketColor);
    }
}