package hackingismakingisengineering.com.languagepronunciationstudy.model;

/**
 * Created by helloworld on 12/2/17.
 */



public enum FrenchPhoneme {

    LATIN_SMALL_LETTER_A("a", "patte", "/pat/", "single-story a", "open oral central unrounded vowel",10),
    LATIN_SMALL_LETTER_ALPHA("ɑ", "pâte", "/pɑt/", "back a", "open oral back unrounded vowel",10),
    LATIN_SMALL_LETTER_ALPHA_WITH_COMBINING_TILDE("ɑ̃", "clan", "/klɑ̃/", "", "open nasal back unrounded vowel",20),
    LATIN_SMALL_LETTER_E("e", "dé", "/de/", "", "clsoe-mid oral unrounded front", 20),
    LATIN_SMALL_LETTER_OPEN_E("ɛ", "belle", "/bɛl/", "epsilon", "open-mid oral front unrounded vowel", 10),
    LATIN_SMALL_LETTER_OPEN_E_WITH_COMBINING_TILDE("ɛ̃", "lin", "/lɛ̃/", "", "open-mid nasal unrounded vowel", 20),
    LATIN_SMALL_LETTER_SCHWA("ə", "demain", "/dəmɛ̃/", "s(c)hwa", "oral cental vowel", 20),
    LATIN_SMALL_LETTER_I("i", "gris", "/gʀi/", "", "close oral front unrounded vowel", 10),
    LATIN_SMALL_LETTER_O("o", "gros", "/gʀo/", "closed o", "close-mid oral back vowel", 20),
    LATIN_SMALL_LETTER_OPEN_O("ɔ", "corps", "/kɔr/", "open o", "open-mid oral back vowel", 30),
    LATIN_SMALL_LETTER_OPEN_O_WITH_COMBINING_TILDE("ɔ̃", "long", "/lɔ̃/", "", "open-mid nasal back vowel", 40),
    LATIN_SMALL_LIGATURE_OE("œ", "leur", "/lœʀ/", "o-e digraph", "open-mid front oral rounded vowel", 40),
    LATIN_SMALL_LIGATURE_OE_WITH_COMBINING_TILDE("œ̃", "brun", "/brœ̃/", "ethel", "open-mid front nasal rounded vowel", 40),
    LATIN_SMALL_LETTER_O_WITH_STROKE("ø", "deux", "/dø/", "barred o", "Close-mid front rounded vowel", 50),
    LATIN_SMALL_LETTER_U("u", "fou", "/fu/", "", "Close back vowel", 20),
    LATIN_SMALL_LETTER_Y("y", "pur", "/pyʀ/", "", "Close oral front rounded vowel", 20),

    //Semi-vowel
    LATIN_SMALL_LETTER_J("j","fille","/fij/","","Palatal approximant", 10),
    LATIN_SMALL_LETTER_TURNED_H("ɥ","huit","/ɥit/","","Labialized palatal approximant", 30),
    LATIN_SMALL_LETTER_W("w","oui","/wi/","","Voiced labio-velar approximant", 10),

    //Constanant
    LATIN_SMALL_LETTER_B("b","bal","/bal/","b","voiced bilabial plosive", 10),
    LATIN_SMALL_LETTER_D("d","dent","/dɑ̃/","d","Voiced alveolar stop", 10),
    LATIN_SMALL_LETTER_F("f","foire","/fwaʀ/","f","Voiceless labiodental fricative", 10),
    LATIN_SMALL_LETTER_G("g","gomme","/gɔm/","g","voiced velar plosive", 10),
    LATIN_SMALL_LETTER_K("k","clé","/kle/","k","voiceless velar plosive", 10),
    LATIN_SMALL_LETTER_L("l","lien","/ljɛ̃/","l","Alveolar lateral approximant", 10 ),
    LATIN_SMALL_LETTER_M("m","mer","/mɛʀ/","m","bilabial nasal", 10 ),
    LATIN_SMALL_LETTER_N("n","nage","/naʒ/","n","alveolar nasal", 10),
    LATIN_SMALL_LETTER_N_WITH_LEFT_HOOK("ɲ","gnon","/ɲɔ̃/","","palatal nasal", 30),
    LATIN_SMALL_LETTER_ENG("ŋ","dancing","/dɑ̃siŋ/","","velar nasal", 30),
    LATIN_CAPITAL_LETTER_R("p","porte","/pɔʀt/","","voiceless bilabial plosive", 10),
    LATIN_CAPITAL_LETTER_("ʀ","rire","/ʀiʀ/","","uvular trill", 50),
    LATIN_SMALL_LETTER_S("s","sang","/sɑ̃/","s","voiceless alveolar fricative", 10),
    LATIN_SMALL_LETTER_ESH("ʃ","chien","/ʃjɛ̃/","","Voiceless palato-alveolar sibilant", 10),
    LATIN_SMALL_LETTER_T("t","train","/tʀɛ̃/","t"," voiceless postalveolar fricative", 10),
    LATIN_SMALL_LETTER_V("v","voile","/vwal/","v","oiced labiodental fricative", 10),
    LATIN_SMALL_LETTER_Z("z","zèbre","/zɛbʀ/","z","voiced alveolar fricative", 10),
    LATIN_CAPITAL_LETTER_EZH("ʒ","jeune","/ʒœn/","","voiced postalveolar fricative", 20);


    private final String symbol;
    private final String example;
    private final String ipa;
    private final String phoneticSymDescription;
    private final String phonologicDescription;
    private final int difficulty;

    public int getDifficulty() {
        return difficulty;
    }

    FrenchPhoneme(String example, String symbol, String ipa, String phoneticSymDescription, String phonologicDescription, int difficulty) {
        this.example = example;
        this.symbol = symbol;
        this.ipa = ipa;
        this.phoneticSymDescription = phoneticSymDescription;
        this.phonologicDescription = phonologicDescription;
        this.difficulty = difficulty;

    }

    public String getExample() {
        return example;
    }

    public String getIpa() {
        return ipa;
    }

    public String getPhoneticSymDescription() {
        return phoneticSymDescription;
    }

    public String getPhonologicDescription() {
        return phonologicDescription;
    }

    public String getSymbol() {
        return symbol;
    }
}