package io.github.thdevonly.zictus.holder;

import android.content.*;
import android.view.*;
import android.widget.*;
import com.unnamed.b.atv.model.*;
import io.github.thdevonly.zictus.*;
import io.github.thdevonly.zictus.R;

public class TreeNodeHolder extends TreeNode.BaseNodeViewHolder<FileNode> {

	Context ctx;
	
    public TreeNodeHolder(Context ctx) {
        super(ctx);
		this.ctx = ctx;
    }

    @Override
    public View createNodeView(TreeNode node, FileNode value) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.custom_node, null, false);

        TextView tvValue = view.findViewById(R.id.node_value);
        ImageView icon = view.findViewById(R.id.node_icon);
		

        tvValue.setText(value.name.isEmpty() ? "/" : value.name);

        if (value.isDirectory) {
            icon.setImageResource(R.drawable.ic_folder);  // Coloca ícone de pasta
        } else {
            icon.setImageResource(R.drawable.ic_folder);    // Coloca ícone de arquivo
        }

        return view;
    }
}
