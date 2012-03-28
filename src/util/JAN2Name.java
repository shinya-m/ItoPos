package util;

import java.net.URL;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

/**
 * JANをネットで検索して商品名に変換
 * @author atsushi-o
 */
public class JAN2Name {
    private static final String RAKUTEN = "http://search.rakuten.co.jp/search/mall/%s/";

    private JAN2Name() {}

    public static String search(String JAN) {
        String ret = "";
        try {
            URL url = new URL(String.format(RAKUTEN, JAN));
            InputStream in = url.openStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.matches("^<div class=\"rsrSResultItemTxt\">$")) {
                    line = br.readLine();
                    String[] s = line.split("<.+?>");
                    ret = s[2];
                    break;
                }
            }
        } catch (Exception ex) {}

        return ret;
    }

    public static void main(String[] args) {
        System.out.println(JAN2Name.search("4902105041826"));
    }
}
