
package io.github.thdevonly.zictus;

import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;
import io.github.thdevonly.zictus.databinding.ActivityMainBinding;
import io.github.thdevonly.zictus.highlighter.JSXHighlighter;
import io.github.thdevonly.zictus.holder.TreeNodeHolder;
import io.github.thdevonly.zictus.languages.JSXLanguage;
import java.io.File;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate and get instance of binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        TreeNode nodes = createLazyFileNode(new java.io.File("/sdcard/"));
        AndroidTreeView tView = new AndroidTreeView(this, nodes);

        tView.setDefaultAnimation(true);
        tView.setUse2dScroll(true);
        tView.setDefaultContainerStyle(R.style.TreeNodeStyleDivided, true);

        binding.drawerContainer.addView(tView.getView());
        
        // set content view to binding's root
        setContentView(binding.getRoot());
        
     //   Pattern pt = Pattern.compile("private|public|protected");
        JSXLanguage.apply(this, binding.codeView);
      // binding.codeView.addSyntaxPattern(pt, Color.BLUE);
    }
    
    private TreeNode createFileTree(File dir) {
		FileNode nodeValue = new FileNode(dir);
		TreeNode node = new TreeNode(nodeValue).setViewHolder(new TreeNodeHolder(this));

		if (dir.isDirectory()) {
			File[] files = dir.listFiles();
			if (files != null) {
				for (File file : files) {
					node.addChild(createFileTree(file));
				}
			}
		}
		return node;
	}

	private TreeNode createLazyFileNode(final File dir) {
		FileNode nodeValue = new FileNode(dir);
		TreeNode node = new TreeNode(nodeValue).setViewHolder(new TreeNodeHolder(this));

		if (dir.isDirectory()) {
			File[] files = dir.listFiles();
			if (files != null) {
				for (final File file : files) {
					// Para os filhos, só adiciona o nó, mas sem carregar seus filhos ainda
					TreeNode childNode = new TreeNode(new FileNode(file)).setViewHolder(new TreeNodeHolder(this));

					if (file.isDirectory()) {
						// Para diretórios, adiciona o click listener para carregar sob demanda
						childNode.setClickListener(new TreeNode.TreeNodeClickListener() {
								@Override
								public void onClick(TreeNode n, Object value) {
									if (n.getChildren().isEmpty()) {
										File[] subFiles = file.listFiles();
										if (subFiles != null) {
											for (File subFile : subFiles) {
												n.addChild(createLazyFileNode(subFile));
											}
										}
									}
								}
							});
					}
					node.addChild(childNode);
				}
			}
		}
		return node;
	}

	private void collapseAll(TreeNode node) {
		for (TreeNode child : node.getChildren()) {
			child.setExpanded(false);
			collapseAll(child);
		}
	}
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }
}
