package io.github.thdevonly.zictus.languages;

import android.content.Context;
import android.text.Editable;
import android.view.KeyEvent;
import com.amrdeveloper.codeview.CodeView;
import io.github.thdevonly.zictus.highlighter.JSXHighlighter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class JSXLanguage {

    public static void apply(Context ctx, CodeView codeView) {
        // Aplica o highlighter
        JSXHighlighter.applyJSXHighlight(ctx, codeView);

        // Configura auto indentação
        codeView.setEnableAutoIndentation(true);

        // Define caracteres de início e fim de indentação
        Set<Character> indentationStarts = new HashSet<>(Arrays.asList('{', '<', '('));
        Set<Character> indentationEnds = new HashSet<>(Arrays.asList('}', '>', ')'));

        codeView.setIndentationStarts(indentationStarts);
        codeView.setIndentationEnds(indentationEnds);

        // Define o tamanho da tabulação
        final int tabLength = 4; // pode ajustar se quiser
        codeView.setTabLength(tabLength);

        // Pair complete
        final Map<Character, Character> pairCompleteMap = new HashMap<>();
        pairCompleteMap.put('{', '}');
        pairCompleteMap.put('[', ']');
        pairCompleteMap.put('(', ')');
        pairCompleteMap.put('<', '>');
        pairCompleteMap.put('"', '"');
        pairCompleteMap.put('\'', '\'');

        codeView.setPairCompleteMap(pairCompleteMap);
        codeView.enablePairComplete(true);
        codeView.enablePairCompleteCenterCursor(true);
    }
}