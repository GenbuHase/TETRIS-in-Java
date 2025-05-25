package io.genbuhase.util;

import java.util.Random;

public enum Color {
	Default(),
	
	Black("0"),
	Red("1"),
	Green("2"),
	Yellow("3"),
	Blue("4"),
	Magenta("5"),
	Cyan("6"),
	White("7"),
	Purple("8;5;13"),
	Orange("8;5;208");
	
	
	
	/**
	 * 未指定状態を表すSGRコマンド
	 */
	private static final String SGR_RESET = toSGRFormat("0");
	
	private static final Random randomizer = new Random();
	
	
	
	private final String colorCode;
	
	private Color () {
		this.colorCode = null;
	}
	
	private Color (String colorCode) {
		this.colorCode = colorCode;
	}
	
	
	
	/**
	 * カラーコードを保持しているか否かを返す
	 * 
	 * @return	カラーコードの有無
	 */
	public boolean hasColor () {
		return colorCode != null;
	}
	
	
	
	@Override
	public String toString () {
		return this.toTextColorFormat().replace("\033", "\\033");
	}
	
	
	
	/**
	 * 文字色を装飾するSGRコマンドを返す
	 * 
	 * @return	"\033[3Xm"
	 */
	public String toTextColorFormat () {
		return hasColor() ? "\033[3%sm".formatted(colorCode) : null;
	}
	
	/**
	 * 背景色を装飾するSGRコマンドを返す
	 * 
	 * @return	"\033[4Xm"
	 */
	public String toBGColorFormat () {
		return hasColor() ? "\033[4%sm".formatted(colorCode) : null;
	}
	
	/**
	 * 文字色を鮮やかに装飾するSGRコマンドを返す
	 * 
	 * @return	"\033[9Xm"
	 */
	public String toVividTextColorFormat () {
		return hasColor() ? "\033[9%sm".formatted(colorCode) : null;
	}
	
	/**
	 * 背景色を鮮やかに装飾するSGRコマンドを返す
	 * 
	 * @return	"\033[10Xm"
	 */
	public String toVividBGColorFormat () {
		return hasColor() ? "\033[10%sm".formatted(colorCode) : null;
	}
	
	/**
	 * 指定されたテキストの文字色を装飾する
	 * 
	 * @param text	テキスト
	 * 
	 * @return		SGRコマンドに内包されたテキスト
	 */
	public String colorText (String text) {
		return hasColor() ? "%s%s%s".formatted(this.toTextColorFormat(), text, SGR_RESET) : text;
	}
	
	public String colorText (char text) {
		return colorText(String.valueOf(text));
	}
	
	/**
	 * 指定されたテキストの背景色を装飾する
	 * 
	 * @param text	テキスト
	 * 
	 * @return		SGRコマンドに内包されたテキスト
	 */
	public String colorBackground (String text) {
		return hasColor() ? "%s%s%s".formatted(this.toBGColorFormat(), text, SGR_RESET) : text;
	}
	
	public String colorBackground (char text) {
		return colorBackground(String.valueOf(text));
	}
	
	/**
	 * 指定されたテキストの文字色を鮮やかに装飾する
	 * 
	 * @param text	テキスト
	 * 
	 * @return		SGRコマンドに内包されたテキスト
	 */
	public String colorTextVividly (String text) {
		return hasColor() ? "%s%s%s".formatted(this.toVividTextColorFormat(), text, SGR_RESET) : text;
	}
	
	public String colorTextVividly (char text) {
		return colorTextVividly(String.valueOf(text));
	}
	
	/**
	 * 指定されたテキストの背景色を鮮やかに装飾する
	 * 
	 * @param text	テキスト
	 * 
	 * @return		SGRコマンドに内包されたテキスト
	 */
	public String colorBackgroundVividly (String text) {
		return hasColor() ? "%s%s%s".formatted(this.toVividBGColorFormat(), text, SGR_RESET) : text;
	}
	
	public String colorBackgroundVividly (char text) {
		return colorBackgroundVividly(String.valueOf(text));
	}
	
	
	
	/**
	 * 指定されたSGRコマンドを表すANSIエスケープシーケンスを返す
	 * 
	 * @param command	SGRコマンド
	 * @return			ANSIエスケープシーケンス
	 */
	public static String toSGRFormat (String command) {
		return "\033[%sm".formatted(command);
	}
	
	/**
	 * 指定されたSGRコマンドに内包されたテキストを返す
	 * 
	 * @param sgrFormat		SGRコマンド
	 * @param text			テキスト
	 * 
	 * @return				SGRコマンドに内包されたテキスト
	 */
	public static String formatText (String sgrFormat, String text) {
		return "%s%s%s".formatted(sgrFormat, text, SGR_RESET);
	}
	
	public static String formatText (String sgrFormat, char text) {
		return formatText(sgrFormat, String.valueOf(text));
	}
	
	public static String getHexColor (int hex) {
		return toSGRFormat("38;5;%d".formatted(hex));
	}
	
	public static String getHexBGColor (int hex) {
		return toSGRFormat("48;5;%d".formatted(hex));
	}
	
	
	
	/**
	 * 指定されたテキストの文字色を虹色にする
	 * 
	 * @param text	テキスト
	 * 
	 * @return		虹色の装飾が施されたテキスト
	 */
	public static String getRainbowColoredText (String text) {
		String result = "";
		
		for (char ch : text.toCharArray()) {
			String coloredText = formatText(getHexColor(randomizer.nextInt(0, 256)), ch);
			result += coloredText;
		}
		
		return result;
	}
	
	/**
	 * 指定されたテキストの背景色を虹色にする
	 * 
	 * @param text	テキスト
	 * 
	 * @return		虹色の装飾が施されたテキスト
	 */
	public static String getRainbowColoredBackground (String text) {
		String result = "";
		
		for (char ch : text.toCharArray()) {
			String coloredText = formatText(getHexBGColor(randomizer.nextInt(0, 256)), ch);
			result += coloredText;
		}
		
		return result;
	}
	
	/**
	 * すべてのHexColorをコンソールに出力する(デバッグ用)
	 */
	public static void __printAllColors () {
		for (int col = 0; col < 16; col++) {
			for (int row = 0; row < 16; row++) {
				int hex = 16 * col + row;
				System.out.print(formatText(getHexColor(hex), "%02X".formatted(hex)) + " ");
			}
			
			System.out.println();
		}
		
		System.out.println();
		
		for (int col = 0; col < 16; col++) {
			for (int row = 0; row < 16; row++) {
				int hex = 16 * col + row;
				System.out.print(formatText(getHexBGColor(hex), "%02X".formatted(hex)) + " ");
			}
			
			System.out.println();
		}
	}
}
