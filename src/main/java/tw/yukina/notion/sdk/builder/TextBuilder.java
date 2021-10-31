package tw.yukina.notion.sdk.builder;

import tw.yukina.notion.sdk.model.common.rich.Text;
import tw.yukina.notion.sdk.model.common.rich.TextObject;
import tw.yukina.notion.sdk.model.common.rich.TextType;
import tw.yukina.notion.sdk.model.helper.RichTextHelper;

import java.net.URL;

public class TextBuilder extends RichTextBuilder<Text> {

    private String content = "";

    private URL url;

    public TextBuilder setContent(String content){
        this.content = content;
        return this;
    }

    public TextBuilder setLink(URL url){
        this.url = url;
        return this;
    }

    @Override
    public Text build() {
        TextObject textObject = new TextObject();
        textObject.setContent(this.content);

        Text text = new Text();
        text.setType(TextType.TEXT);
        text.setTextObject(textObject);
        text.setPlainText(this.content);
        setRichTextStyle(text);

        if(this.url != null){
            RichTextHelper.setLinkToText(text, this.url);
        }

        return text;
    }
}
