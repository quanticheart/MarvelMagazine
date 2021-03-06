package qunaticheart.com.marvelmagazine.Utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

import qunaticheart.com.marvelmagazine.Conexao.Model.MagazineData;
import qunaticheart.com.marvelmagazine.Controller.MainController;
import qunaticheart.com.marvelmagazine.R;
import qunaticheart.com.marvelmagazine.View.MagazineActivity;

import static qunaticheart.com.marvelmagazine.Utils.Adapter.AdapterMagazineList.getMagazineKey;
import static qunaticheart.com.marvelmagazine.Utils.GlideUtil.initGlide;
import static qunaticheart.com.marvelmagazine.Utils.ViewUtil.getMagazineTitleWithoutNumber;

public class DialogUtil {

    private static DialogRemoveLike removeLike;

    public static void showDetails(final Activity activity, final MagazineData magazine, final int position) {

        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_info_magazine);

        initGlide(activity, ViewUtil.getFantasticImageSquare(magazine.getThumbnail().getPath(), magazine.getThumbnail().getExtension()), ((ImageView) dialog.findViewById(R.id.magazineCover)));

        ((TextView) dialog.findViewById(R.id.magazineName)).setText(getMagazineTitleWithoutNumber(magazine.getTitle()));
        ((TextView) dialog.findViewById(R.id.numberMagazine)).setText(ViewUtil.getNumberFormated(magazine.getTitle(), magazine.getIssueNumber()));
        ((TextView) dialog.findViewById(R.id.numberPrice)).setText(ViewUtil.moneyFormate(magazine.getPrices().get(0).getPrice()));

        dialog.findViewById(R.id.btnMoreDetails).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent magazineIntent = new Intent(activity, MagazineActivity.class);
                magazineIntent.putExtra(getMagazineKey(), magazine);
                ActivityUtil.callActivity(activity, magazineIntent, false);
                dialog.dismiss();

            }
        });
        final ImageView imageViewLike = dialog.findViewById(R.id.btn_like);
        ViewUtil.statusLikeView(imageViewLike, magazine);
        imageViewLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                magazine.setLike(ViewUtil.updateMagazineLikeStatus(activity, magazine));
                ViewUtil.statusLikeView(imageViewLike, magazine);
                new MainController(activity).setLike(magazine);

                if (magazine.getLike().equals("0")) {
                    removeLike.removeLike(position);
                    dialog.dismiss();
                }
            }
        });

        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(true);
        dialog.show();
    }

    public static void setRemoveLike(DialogRemoveLike removeLike) {
        DialogUtil.removeLike = removeLike;
    }

    public interface DialogRemoveLike {
        void removeLike(int position);
    }


//
//
//    }

}
