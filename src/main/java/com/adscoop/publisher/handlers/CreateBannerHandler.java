package com.adscoop.publisher.handlers;


import com.adscoop.entiites.BannerNode;
import com.adscoop.entiites.Category;
import com.adscoop.entiites.UserNode;
import com.adscoop.publisher.mappers.MapperUtil;
import com.adscoop.publisher.services.BannerPublisherService;
import com.adscoop.publisher.services.UploadFileService;
import com.google.inject.Inject;
import javafx.beans.binding.IntegerBinding;
import ratpack.form.Form;
import ratpack.form.UploadedFile;
import ratpack.handling.Context;
import ratpack.handling.Handler;


import java.io.File;

/**
 * Created by thokle on 27/11/2016.
 */
public class CreateBannerHandler implements Handler {


    private BannerPublisherService bannerPublisherService;

    private UploadFileService uploadFileService;
    @Inject
    public CreateBannerHandler(BannerPublisherService bannerPublisherService, UploadFileService uploadFileService) {
        this.bannerPublisherService = bannerPublisherService;
        this.uploadFileService = uploadFileService;

    }

    @Override
    public void handle(Context ctx) throws Exception {
        String token = ctx.getRequest().getHeaders().get("token").toString();
        if (token != null) {
            ctx.parse(Form.class).then(form -> {
                UserNode userNode = bannerPublisherService.findUserNodeByToken(token);
                BannerNode bannerNode = new BannerNode();

                bannerNode.setJavaScriptUrl("http://"+form.get("bannerSpaceToken"));
                bannerNode.setHeight(Integer.valueOf(form.get("height")));
                UploadedFile file = form.file("url");

              String path =   uploadFileService.uploadfile(null,file,token);
                bannerNode.setPictureUrl(path);

                userNode.addBanner(bannerNode);
                bannerPublisherService.save(userNode);
            });

        } else {
            ctx.render("NO USER TOKEN IN HEADER");
        }
    }
}
