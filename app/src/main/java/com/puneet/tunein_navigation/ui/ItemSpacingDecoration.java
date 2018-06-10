package com.puneet.tunein_navigation.ui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

class ItemSpacingDecoration extends RecyclerView.ItemDecoration {

    private int space;
    private int tabletHorizontalSpacing = -1;

    private HashMap<Integer, Integer> cachedPositionsForRemovalAnimations = new HashMap<>();
    private Set<Integer> ignoreDecorationVerticalSet = new TreeSet<>((lhs, rhs) -> rhs.compareTo(lhs));
    private Set<Integer> halfSpacingDividerDesign = new TreeSet<>((lhs, rhs) -> rhs.compareTo(lhs));

     ItemSpacingDecoration(int space) {
        this.space = space;
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            int position = parent.findContainingViewHolder(child).getAdapterPosition();
            if (position == RecyclerView.NO_POSITION) {
                position = cachedPositionsForRemovalAnimations.get(child.hashCode());
            } else {
                cachedPositionsForRemovalAnimations.put(child.hashCode(), position);
            }
            if (ignoreDecorationVerticalSet.contains(position)) {
                continue;
            }

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int startX = child.getPaddingLeft() + child.getLeft();
            int stopX = child.getRight() - child.getPaddingRight();
            int y = child.getBottom() + params.bottomMargin + (halfSpacingDividerDesign.contains(position) ? (space / 2) : space);
            Paint paint = new Paint();
            paint.setColor(Color.BLACK);
            c.drawLine(startX, y, stopX, y, paint);
        }
    }

}
