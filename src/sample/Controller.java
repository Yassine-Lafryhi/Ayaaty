package sample;

import java.io.IOException;
import java.util.Iterator;
import java.util.Random;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Controller {
    MediaPlayer SaadMP;
    MediaPlayer FaresMP;
    MediaPlayer AhmedMP;
    boolean IsSaadMPPlaying = false;
    boolean IsFaresMPPlaying = false;
    boolean IsAhmedMPPlaying = false;
    @FXML
    Label Prayer1;
    @FXML
    Label Prayer2;
    @FXML
    Label Prayer3;
    @FXML
    Label Prayer4;
    @FXML
    Label Prayer5;
    @FXML
    Button SB1;
    @FXML
    Button SB2;
    @FXML
    Button S;
    @FXML
    Button F;
    @FXML
    Button A;
    @FXML
    Label PQTF;
    @FXML
    Label PNTF;
    @FXML
    Label Title;
    @FXML
    Label MS;
    @FXML
    Label QK;
    @FXML
    Label P1;
    @FXML
    Label P2;
    @FXML
    Label P3;
    @FXML
    Label P4;
    @FXML
    Label P5;
    @FXML
    Label AQL;
    @FXML
    Label AAL;
    String[] AQ = new String[]{"ربنا أفرغ علينا صبرا وثبت أقدامنا وانصرنا على القوم الكافرين", "ربنا لا تؤاخذنا إن نسينا أو أخطأنا ربنا ولا تحمل علينا إصرا كما حملته على الذين من قبلنا ربنا ولا تحملنا ما لا طاقة لنا به واعف عنا واغفر لنا وارحمنا أنت مولانا فانصرنا على القوم الكافرين", "ربنا لا تزغ قلوبنا بعد إذ هديتنا وهب لنا من لدنك رحمة إنك أنت الوهاب", "ربنا إننا آمنا فاغفر لنا ذنوبنا وقنا عذاب النار", "رب هب لي من لدنك ذرية طيبة إنك سميع الدعاء", "ربنا آمنا بما أنزلت واتبعنا الرسول فاكتبنا مع الشاهدين", "ربنا اغفر لنا ذنوبنا وإسرافنا في أمرنا وثبت أقدامنا وانصرنا على القوم الكافرين", "ربنا ما خلقت هذا باطلا سبحانك فقنا عذاب النار ربنا إنك من تدخل النار فقد أخزيته وما للظالمين من أنصار ربنا إننا سمعنا مناديا ينادي للإيمان أن آمنوا بربكم فآمنا ربنا فاغفر لنا ذنوبنا وكفر عنا سيئاتنا وتوفنا مع الأبرار ربنا وآتنا ما وعدتنا على رسلك ولا تخزنا يوم القيامة إنك لا تخلف الميعاد", "ربنا ظلمنا أنفسنا وإن لم تغفر لنا وترحمنا لنكونن من الخاسرين", "ربنا لا تجعلنا مع القوم الظالمين", "ربنا أفرغ علينا صبرا وتوفنا مسلمين", "ربنا لا تجعلنا فتنة للقوم الظالمين ونجنا برحمتك من القوم الكافرين", "رب إني أعوذ بك أن أسألك ما ليس لي به علم وإلا تغفر لي وترحمني أكن من الخاسرين", "رب اجعلني مقيم الصلاة ومن ذريتي ربنا وتقبل دعاء", "ربنا اغفر لي ولوالدي وللمؤمنين يوم يقوم الحساب", "رب أدخلني مدخل صدق وأخرجني مخرج صدق واجعل لي من لدنك سلطانا نصيرا", "ربنا آتنا من لدنك رحمة وهيئ لنا من أمرنا رشدا", "رب اشرح لي صدري ويسر لي أمري واحلل عقدة من لساني يفقهوا قولي", "رب زدني علما", "لا إله إلا أنت سبحانك إني كنت من الظالمين", "رب لا تذرني فردا وأنت خير الوارثين", "رب أعوذ بك من همزات الشياطين وأعوذ بك رب أن يحضرون", "ربنا آمنا فاغفر لنا وارحمنا وأنت خير الراحمين", "رب اغفر وارحم وأنت خير الراحمين"};
    String[] AN = new String[]{"سبحان الله", "سبحان الله وبحمده", "سبحان الله والحمد لله", "سبحان الله العظيم وبحمده", "سبحان الله وبحمده ، سبحان الله العظيم", "لا إله إلا الله وحده لا شريك له، له الملك وله الحمد وهو على كل شيء قدير", "لا حول ولا قوة إلا بالله", "الحمد لله رب العالمين", "اللهم صل وسلم وبارك على سيدنا محمد", "أستغفر الله", "سبحان الله، والحمد لله، ولا إله إلا الله، والله أكبر", "لا إله إلا الله", "الله أكبر", "سبحان الله ، والحمد لله ، ولا إله إلا الله ، والله أكبر ، اللهم اغفر لي ، اللهم ارحمني ، اللهم ارزقني", "الحمد لله حمدا كثيرا طيبا مباركا فيه", "الله أكبر كبيرا ، والحمد لله كثيرا ، وسبحان الله بكرة وأصيلا", "اللهم صل على محمد وعلى آل محمد كما صليت على إبراهيم , وعلى آل إبراهيم إنك حميد مجيد , اللهم بارك على محمد وعلى آل محمد كما باركت على إبراهيم وعلى آل إبراهيم إنك حميد مجيد"};

    public Controller() {
    }

    public void initialize() {
        Document doc = null;

        try {
            doc = Jsoup.connect("http://www.habous.gov.ma/horaire%20de%20priere/horaire-pub.php?ville=23").get();
            Elements newsHeadlines = doc.select(".horaire");
            Iterator var3 = newsHeadlines.iterator();

            while(var3.hasNext()) {
                Element headline = (Element)var3.next();
                String All = headline.html();
                String[] Lines = All.split("\n");
                All = "";
                String[] Prayers = Lines;
                int var8 = Lines.length;

                int var9;
                String Line;
                for(var9 = 0; var9 < var8; ++var9) {
                    Line = Prayers[var9];
                    if (Line.contains("td")) {
                        All = All + Line + "\n";
                    }
                }

                Lines = All.split("\n");
                All = "";
                Prayers = Lines;
                var8 = Lines.length;

                for(var9 = 0; var9 < var8; ++var9) {
                    Line = Prayers[var9];
                    char[] LineArray = Line.toCharArray();
                    boolean CanCopy = false;
                    char[] var13 = LineArray;
                    int var14 = LineArray.length;

                    for(int var15 = 0; var15 < var14; ++var15) {
                        char Char = var13[var15];
                        if (Char == '<') {
                            CanCopy = false;
                        }

                        if (Char == '>') {
                            CanCopy = true;
                        }

                        if (CanCopy) {
                            All = All + Char;
                        }
                    }

                    All = All + "\n";
                }

                All = All.replace("<", "");
                All = All.replace(">", "");
                All = All.replace(" ", "");
                Prayers = All.split("\n");
                this.Prayer1.setText(Prayers[1]);
                this.Prayer2.setText(Prayers[5]);
                this.Prayer3.setText(Prayers[7]);
                this.Prayer4.setText(Prayers[9]);
                this.Prayer5.setText(Prayers[11]);
                this.ShowAnotherPN();
                this.ShowAnotherPQ();
            }
        } catch (IOException var17) {
            var17.printStackTrace();
        }

    }

    public void Saad() {
        if (this.AhmedMP != null && this.AhmedMP.getStatus().equals(Status.PLAYING)) {
            this.AhmedMP.stop();
        }

        if (this.FaresMP != null && this.FaresMP.getStatus().equals(Status.PLAYING)) {
            this.FaresMP.stop();
        }

        this.IsSaadMPPlaying = !this.IsSaadMPPlaying;
        if (this.IsSaadMPPlaying) {
            Random rand = new Random();
            int n = rand.nextInt(114);
            ++n;
            String Name = String.format("%03d", n);
            String bip = "https://download.tvquran.com/download/TvQuran.com__Al-Ghamdi/" + Name + ".mp3";
            Media hit = new Media(bip);
            this.SaadMP = new MediaPlayer(hit);
            this.SaadMP.play();
        } else {
            this.SaadMP.stop();
        }

    }

    public void Fares() {
        if (this.AhmedMP != null && this.AhmedMP.getStatus().equals(Status.PLAYING)) {
            this.AhmedMP.stop();
        }

        if (this.SaadMP != null && this.SaadMP.getStatus().equals(Status.PLAYING)) {
            this.SaadMP.stop();
        }

        this.IsFaresMPPlaying = !this.IsFaresMPPlaying;
        if (this.IsFaresMPPlaying) {
            Random rand = new Random();
            int n = rand.nextInt(114);
            ++n;
            String Name = String.format("%03d", n);
            String bip = "https://download.tvquran.com/download/TvQuran.com__Fares.Abbad/" + Name + ".mp3";
            Media hit = new Media(bip);
            this.FaresMP = new MediaPlayer(hit);
            this.FaresMP.play();
        } else {
            this.FaresMP.stop();
        }

    }

    public void Ahmed() {
        if (this.SaadMP != null && this.SaadMP.getStatus().equals(Status.PLAYING)) {
            this.SaadMP.stop();
        }

        if (this.FaresMP != null && this.FaresMP.getStatus().equals(Status.PLAYING)) {
            this.FaresMP.stop();
        }

        this.IsAhmedMPPlaying = !this.IsAhmedMPPlaying;
        if (this.IsAhmedMPPlaying) {
            Random rand = new Random();
            int n = rand.nextInt(114);
            ++n;
            String Name = String.format("%03d", n);
            String bip = "https://download.tvquran.com/download/TvQuran.com__Al-Ajmy/" + Name + ".mp3";
            Media hit = new Media(bip);
            this.AhmedMP = new MediaPlayer(hit);
            this.AhmedMP.play();
        } else {
            this.AhmedMP.stop();
        }

    }

    public void SetTextIn(Label Label, String Text) {
        int WordsCounter = 0;
        String[] Words = Text.split(" ");
        String[] var5 = Words;
        int var6 = Words.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            String word = var5[var7];
            if (WordsCounter != 0 && WordsCounter % 10 == 0) {
                Label.setText(Label.getText() + word + " \n");
            } else {
                Label.setText(Label.getText() + word + " ");
            }

            ++WordsCounter;
        }

    }

    public void ShowAnotherPQ() {
        this.PQTF.setText("");
        Random rand = new Random();
        int n = rand.nextInt(24);
        this.SetTextIn(this.PQTF, this.AQ[n]);
    }

    public void ShowAnotherPN() {
        this.PNTF.setText("");
        Random rand = new Random();
        int n = rand.nextInt(17);
        this.SetTextIn(this.PNTF, this.AN[n]);
    }
}