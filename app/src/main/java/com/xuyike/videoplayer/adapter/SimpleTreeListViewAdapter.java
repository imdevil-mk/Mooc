package com.xuyike.videoplayer.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.imdevil.mooc.R;
import com.xuyike.videoplayer.Utils.*;
import com.xuyike.videoplayer.Utils.adapter.TreeListViewAdapter;

public class SimpleTreeListViewAdapter<T> extends TreeListViewAdapter<T>
{
	public SimpleTreeListViewAdapter(ListView tree, Context context,
			List<T> datas, int defaultExpandLevel)
			throws IllegalArgumentException, IllegalAccessException
	{
		super(tree, context, datas, defaultExpandLevel);
	}

	@Override
	public View getConvertView(Node node, int position, View convertView,
			ViewGroup parent)
	{
		ViewHolder holder = null;
		if (convertView == null)
		{
			convertView = mInflater.inflate(R.layout.list_item, parent, false);
			holder = new ViewHolder();
			holder.mIcon = (ImageView) convertView
					.findViewById(R.id.id_item_icon);
			holder.mText = (TextView) convertView
					.findViewById(R.id.id_item_text);
			convertView.setTag(holder);
		} else
		{
			holder = (ViewHolder) convertView.getTag();
		}
		if (node.getIcon() == -1)
		{
			holder.mIcon.setVisibility(View.INVISIBLE);
		} else
		{
			holder.mIcon.setVisibility(View.VISIBLE);
			holder.mIcon.setImageResource(node.getIcon());
		}

		holder.mText.setText(node.getName());

		return convertView;
	}

	private class ViewHolder
	{
		ImageView mIcon;
		TextView mText;
	}

	/**
	 * 动态插入节点
	 * 
	 * @param position
	 * @param string
	 */
	public void addExtraNode(int position, String string)
	{
		Node node = mVisibleNodes.get(position);
		int indexOf = mAllNodes.indexOf(node);
		// Node
		Node extraNode = new Node(-1, node.getId(), string);
		extraNode.setParent(node);
		node.getChildren().add(extraNode);
		mAllNodes.add(indexOf + 1, extraNode);

		mVisibleNodes = TreeHelper.filterVisibleNodes(mAllNodes);
		notifyDataSetChanged();

	}

}
