package com.hy.myfuck.view;


import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class RollViewPager extends ViewPager {
	// 用来保存手指点下时对应的x、y坐标
	int downX;
	int downY;

	public RollViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 负责处理事件分发机制 手指点下的点下，操作，抬起的时间 都由MotionEvent获取
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		/*
		 * ev.getAction()获取手指对当前控件的操作
		 * 如果是点下的动作，那返回MotionEvent.ACTION_DOWN
		 * 如果是移动的动作，那返回MotionEvent.ACTION_MOVE
		 * 如果是抬起的动作，那返回MotionEvent.ACTION_UP
		 *
		 */
		switch (ev.getAction()) {
			case MotionEvent.ACTION_DOWN:
			/*
			 * 父布局不允许拦截事件：true：父布局不拦截事件可以向内传递 false：父布局拦截事件，不向内传递
			 */
				getParent().requestDisallowInterceptTouchEvent(true);
				downX = (int) ev.getX();
				downY = (int) ev.getY();
				break;
			case MotionEvent.ACTION_MOVE:

				int moveX = (int) ev.getX();
				int moveY = (int) ev.getY();
				// 在X轴上的位移量小于在Y轴上的位移量
				if (Math.abs(moveX - downX) < Math.abs(moveY - downY)) {
					// 让父控件去拦截事件
					getParent().requestDisallowInterceptTouchEvent(false);
				}
				//在X轴的位移量 大于在Y轴上的位移量
				else{
				/*
				 * 1.从左向右滑动 如果处于第一张图片  要让父控件拦截事件
				 * 2.从左向右滑动  如果不是第一张图片  要让父控件不拦截事件
				 * 3.从右往左滑动 如果处于最后一张图片 要让父控件拦截事件
				 * 4.从右往左滑动 如果不是最后一张图片 要让父控件不拦截事件
				 */
					if(downX<moveX&&getCurrentItem()==0){
						getParent().requestDisallowInterceptTouchEvent(false);
					}
					else if(downX<moveX&&getCurrentItem()!=0){
						getParent().requestDisallowInterceptTouchEvent(true);
					}
					else if(downX>moveX&&getCurrentItem()==getAdapter().getCount()-1){
						getParent().requestDisallowInterceptTouchEvent(false);
					}
					else if(downX>moveX&&getCurrentItem()!=getAdapter().getCount()-1){
						getParent().requestDisallowInterceptTouchEvent(true);
					}



				}

			default:
				break;
		}
		return super.dispatchTouchEvent(ev);
	}
}
