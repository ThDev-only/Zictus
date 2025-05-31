package io.github.thdevonly.zictus;

import android.app.*;
import android.os.*;
import com.unnamed.b.atv.model.*;
import com.unnamed.b.atv.view.*;
import io.github.thdevonly.zictus.databinding.*;
import io.github.thdevonly.zictus.holder.*;
import java.io.*;

public class MainActivity extends Activity {
    MainBinding binding; 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MainBinding.inflate(getLayoutInflater());
        

        TreeNode nodes = createLazyFileNode(new File("/sdcard/"));
        AndroidTreeView tView = new AndroidTreeView(this, nodes);

        tView.setDefaultAnimation(true);
        tView.setUse2dScroll(true);
        tView.setDefaultContainerStyle(R.style.TreeNodeStyleDivided, true);

        binding.getRoot().addView(tView.getView());
		
		setContentView(R.layout.main);
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
}
