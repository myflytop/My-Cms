import cn.hutool.core.util.StrUtil;

public class A {
	  private static String urlFilter(String url) {
	        if (null != url) {
	            final boolean urlEndsWithHtmlPostFix = url.endsWith(".html") || url.endsWith(".htm");
	            if (urlEndsWithHtmlPostFix) {
	                return url.substring(0, url.lastIndexOf("."));
	            }
	        }
	        return StrUtil.replace(url, " ", "-");
	    }
	public static void main(String[] args) {
		String url=StrUtil.replaceChars(" 0p 2 3  "," ","");
		System.out.println(url);
		System.out.println(urlFilter(url));
	}
}
