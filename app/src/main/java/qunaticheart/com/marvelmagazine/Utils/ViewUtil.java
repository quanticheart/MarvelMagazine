package qunaticheart.com.marvelmagazine.Utils;

import android.app.Activity;
import android.text.Html;
import android.text.Spanned;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import java.text.NumberFormat;
import java.util.List;

import qunaticheart.com.marvelmagazine.Conexao.Model.MagazineData;
import qunaticheart.com.marvelmagazine.R;

public class ViewUtil {

    //==============================================================================================
    //
    // String Utils
    //
    //==============================================================================================

    /**
     * @param timestamp to format
     * @return return data
     */
    public static String timestampToStringFormated(List<MagazineData.dates> timestamp) {
        String date[] = timestamp.get(0).getDate().split("T");
        return date[0];
    }

    /**
     * @param number to format
     * @return formated
     */
    public static String moneyFormate(float number) {
        return NumberFormat.getCurrencyInstance().format(number);
    }

    /**
     * @param number to format
     * @return formated
     */
    public static String pageCountFormate(int number) {
        if (number <= 0) {
            return "";
        } else if (number == 1) {
            return "One Page";
        } else {
            return number + " Pages";
        }
    }


    public static String getNumberFormated(String title, Integer number) {
        if (title.toLowerCase().contains("poster")) {
            return "Poster";
        } else {
            return "#" + number;
        }
    }

    public static String getMagazineFormat(String format) {
        if (format.equals(format.toUpperCase())) {
            return "Poster";
        } else {
            return format;
        }
    }

    public static String getMagazineTitleWithoutNumber(String Magazinetitle) {
        String[] title = Magazinetitle.split("#");
        return title[0];
    }

    public static Spanned getMagazineDescription(String description) {
        if (description == null) {
            return Html.fromHtml("No Description");
        } else {
            return Html.fromHtml(description);
        }
    }

    //==============================================================================================
    //
    // Like Button ImageView Utils
    //
    //==============================================================================================

    public static void statusLikeView(ImageView imageView, MagazineData magazine) {

        AlphaAnimation alphaAnimationShowIcon = new AlphaAnimation(0.2f, 1.0f);
        alphaAnimationShowIcon.setDuration(500);

        if (magazine.getLike().equals("0")) {
            imageView.setImageResource(R.drawable.ic_favorite_border_black_24dp);
            imageView.startAnimation(alphaAnimationShowIcon);
        } else {
            imageView.setImageResource(R.drawable.ic_favorite_black_24dp);
            imageView.startAnimation(alphaAnimationShowIcon);
        }
    }

    public static String updateMagazineLikeStatus(Activity activity, MagazineData magazine) {
        if (magazine.getLike().equals("0")) {
            LoggerUtils.showMsg(activity, activity.getString(R.string.msg_liked));
            return "1";
        } else {
            LoggerUtils.showMsg(activity, activity.getString(R.string.msg_desliked));
            return "0";
        }
    }

    //==============================================================================================
    //
    // Image Utils
    //
    //==============================================================================================

    /**
     * @param url       url for connection
     * @param extencion extencion image
     * @return image url
     */
    public static String getFullImageCover(String url, String extencion) {
        return url + "." + extencion;
    }

    /**
     * @param url       url for connection
     * @param extencion extencion image
     * @return image url
     */
    public static String getFantasticImageCover(String url, String extencion) {
        return url + "/" + getPortraitFantastic() + "." + extencion;
    }

    /**
     * @param url       url for connection
     * @param extencion extencion image
     * @return image url Square
     */
    static String getFantasticImageSquare(String url, String extencion) {
        return url + "/" + getSquareFantastic() + "." + extencion;
    }


//========================================================================
//    Utils

    //cover
    private static String getPortraitFantastic() {
        return "portrait_fantastic";
    }

    //Square
    private static String getSquareFantastic() {
        return "standard_fantastic";
    }

//    portrait_small	50x75px
//    portrait_medium	100x150px
//    portrait_xlarge	150x225px
//    portrait_fantastic	168x252px
//    portrait_uncanny	300x450px
//    portrait_incredible	216x324px
//    Standard (square) aspect ratio

//    Square
//    standard_small	65x45px
//    standard_medium	100x100px
//    standard_large	140x140px
//    standard_xlarge	200x200px
//    standard_fantastic	250x250px
//    standard_amazing	180x180px
}
