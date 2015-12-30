import android.graphics.Bitmap;
import android.graphics.Color;
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
}