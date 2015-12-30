import android.graphics.Bitmap;
import android.graphics.Color;
import java.util.HashSet;
import java.util.Set;

public class ColorHelper{
		/**
	 * 计算bitmap的RGB值,取RGB平均值
	 * 
	 * @param mBitmap
	 * @return max_color
	 */
	public static int bitmapGetAvePixelColor(Bitmap mBitmap) {
		int tr = 0;
		int tg = 0;
		int tb = 0;
		int harmonic_color = 0;
		try {
			int picw = mBitmap.getWidth();
			int pich = mBitmap.getHeight();
			int index = 0;

			while ((index < picw) && (index < pich)) {

				// 取得像素点
				int value = mBitmap.getPixel(index, index);
				if (value != 0) {
					int R = (value >> 16) & 0xff;// 取高两位
					int G = (value >> 8) & 0xff;// 取中两位
					int B = value & 0xff;// 取低两位

					tr += R;// r总和
					tg += G;// g总和
					tb += B;// b总和

				}
				index++;
			}
			int modify_color_r = (int) tr / index;// r平均值
			int modify_color_g = (int) tg / index;// g平均值
			int modify_color_b = (int) tb / index;// b平均值

			// 得到平均颜色值
			harmonic_color = Color.rgb(modify_color_r, modify_color_g,
					modify_color_b);

			// 返回一个颜色值
			return harmonic_color;
		} catch (Exception e) {
			e.printStackTrace();
			return Color.WHITE;
		}
	}


	/**
	 * 计算bitmap的RGB值,根据endSize大小返回RGB数组 mBitmap:传入需要计算的bitmap;endSize:返回多少位行素值
	 * ranArr:返回一个RGB数组
	 * 
	 * @param mBitmap
	 * @param endSize
	 * @return ranArr
	 */
	public static Integer[] bitmapGetPiexs(Bitmap mBitmap, int endSize) {
		int picw = mBitmap.getWidth();
		int pich = mBitmap.getHeight();
		int color = 0;
		Integer[] ranArr;
		Set<Integer> set = new HashSet<Integer>();

		int i = 0;
		while ((i < picw) && (i < pich)) {

			int value = mBitmap.getPixel(i, i);
			if (value != 0) {
				int R = (value >> 16) & 0xff;
				int G = (value >> 8) & 0xff;
				int B = value & 0xff;
				color = Color.rgb(R, G, B);
				set.add(color);// 存入不重复的color值
				Log.i("Pixel Value", "pixel: " + Color.rgb(R, G, B));

			}
			i++;
		}
		int size = set.size();
		if (size < endSize) {
			int differSize = endSize - set.size();
			size += differSize;

			for (int j = 1; j < differSize + 1; j++) {
				set.add(color + j);
			}
		}
		ranArr = new Integer[size];
		ranArr = set.toArray(new Integer[size]);
		set.clear();
		return ranArr;
	}
}
