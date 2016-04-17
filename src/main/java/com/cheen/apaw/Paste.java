package com.cheen.apaw;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cheen on 4/16/2016.
 */
public class Paste
{

    public enum PastePrivacy
    {
        PUBLIC,
        UNLISTED,
        PRIVATE;
    }

    public enum PasteExpiration
    {
        NEVER,
        MINUTES_10,
        HOUR_1,
        DAY_1,
        WEEK_1,
        WEEK_2,
        MONTH_1;
    }

    public enum PasteFormat
    {
        ABAP,
        ACTIONSCRIPT,
        ACTIONSCRIPT3,
        ADA,
        AIMMS,
        ALGOL68,
        APACHE,
        APPLESCRIPT,
        APT_SOURCES,
        ARM,
        ASM,
        ASP,
        ASYMPTOTE,
        AUTOCONF,
        AUTOHOTKEY,
        AUTOIT,
        AVISYNTH,
        AWK,
        BASCOMAVR,
        BASH,
        BASIC4GL,
        DOS,
        BIBTEX,
        BLITZBASIC,
        B3D,
        BMX,
        BNF,
        BOO,
        BF,
        C,
        C_WINAPI,
        C_MAC,
        CIL,
        CSHARP,
        CPP,
        CPP_WINAPI,
        CPP_QT,
        C_LOADRUNNER,
        CADDCL,
        CADLISP,
        CFDG,
        CHAISCRIPT,
        CHAPEL,
        CLOJURE,
        KLONEC,
        KLONECPP,
        CMAKE,
        COBOL,
        COFFEESCRIPT,
        CFM,
        CSS,
        CUESHEET,
        D,
        DART,
        DCL,
        DCPU16,
        DCS,
        DELPHI,
        OXYGENE,
        DIFF,
        DIV,
        DOT,
        E,
        EZT,
        ECMASCRIPT,
        EIFFEL,
        EMAIL,
        EPC,
        ERLANG,
        FSHARP,
        FALCON,
        FO,
        F1,
        FORTRAN,
        FREEBASIC,
        FREESWITCH,
        GAMBAS,
        GML,
        GDB,
        GENERO,
        GENIE,
        GETTEXT,
        GO,
        GROOVY,
        GWBASIC,
        HASKELL,
        HAXE,
        HICEST,
        HQ9PLUS,
        HTML4STRICT,
        HTML5,
        ICON,
        IDL,
        INI,
        INNO,
        INTERCAL,
        IO,
        ISPFPANEL,
        J,
        JAVA,
        JAVA5,
        JAVASCRIPT,
        JCL,
        JQUERY,
        JSON,
        JULIA,
        KIXTART,
        LATEX,
        LDIF,
        LB,
        LSL2,
        LISP,
        LLVM,
        LOCOBASIC,
        LOGTALK,
        LOLCODE,
        LOTUSFORMULAS,
        LOTUSSCRIPT,
        LSCRIPT,
        LUA,
        M68K,
        MAGIKSF,
        MAKE,
        MAPBASIC,
        MATLAB,
        MIRC,
        MMIX,
        MODULA2,
        MODULA3,
        MPASM,
        MXML,
        MYSQL,
        NAGIOS,
        NETREXX,
        NEWLISP,
        NGINX,
        NIMROD,
        TEXT,
        NSIS,
        OBERON2,
        OBJECK,
        OBJC,
        OCAML_BRIEF,
        OCAML,
        OCTAVE,
        PF,
        GLSL,
        OOBAS,
        ORACLE11,
        ORACLE8,
        OZ,
        PARASAIL,
        PARIGP,
        PASCAL,
        PAWN,
        PCRE,
        PER,
        PERL,
        PERL6,
        PHP,
        PHP_BRIEF,
        PIC16,
        PIKE,
        PIXELBENDER,
        PLSQL,
        POSTGRESQL,
        POSTSCRIPT,
        POVRAY,
        POWERSHELL,
        POWERBUILDER,
        PROFTPD,
        PROGRESS,
        PROLOG,
        PROPERTIES,
        PROVIDEX,
        PUPPET,
        PUREBASIC,
        PYCON,
        PYTHON,
        PYS60,
        Q,
        QBASIC,
        QML,
        RSPLUS,
        RACKET,
        RAILS,
        RBS,
        REBOL,
        REG,
        REXX,
        ROBOTS,
        RPMSPEC,
        RUBY,
        GNUPLOT,
        RUST,
        SAS,
        SCALA,
        SCHEME,
        SCILAB,
        SCL,
        SDLBASIC,
        SMALLTALK,
        SMARTY,
        SPARK,
        SPARQL,
        SQF,
        SQL,
        STANDARDML,
        STONESCRIPT,
        SCLANG,
        SWIFT,
        SYSTEMVERILOG,
        TSQL,
        TCL,
        TERATERM,
        THINBASIC,
        TYPOSCRIPT,
        UNICON,
        USCRIPT,
        UPS,
        URBI,
        VALA,
        VBNET,
        VBSCRIPT,
        VEDIT,
        VERILOG,
        VHDL,
        VIM,
        VISUALPROLOG,
        VB,
        VISUALFOXPRO,
        WHITESPACE,
        WHOIS,
        WINBATCH,
        XBASIC,
        XML,
        XORG_CONF,
        XPP,
        YAML,
        Z80,
        ZXBASIC;
    }

    private String apiKey;
    private String pasteData;
    private PastePrivacy pastePrivacy;
    private String pasteName;
    private PasteExpiration pasteExpiration;
    private PasteFormat pasteFormat;
    private String userKey;

    public Paste()
    {
        this.pasteData = null;
        this.apiKey = null;
        this.pasteName = null;
        this.userKey = null;
    }

    public String send() throws Exception
    {
        if(pasteData == null)
        {
            throw new Exception("Paste data null");
        }
        if(apiKey == null)
        {
            throw new Exception("APIKey null");
        }
        if(pasteName == null)
        {
            throw new Exception("PasteName null");
        }

        Map<String, Object> fields = new HashMap<String, Object>();
        fields.put("api_dev_key", apiKey);
        fields.put("api_paste_code", pasteData);
        fields.put("api_paste_name", pasteName);

        if(pastePrivacy != null)
        {
            String item = "";
            switch(pastePrivacy)
            {
                case PUBLIC:
                    item = "0";
                    break;

                case UNLISTED:
                    item = "1";
                    break;

                case PRIVATE:
                    item = "2";
                    break;

                default:
                    throw new Exception("Unusual paste privacy option supplied");
            }
            fields.put("api_paste_private", item);
        }
        if(pasteExpiration != null)
        {
            String item = "";
            switch(pasteExpiration)
            {

                case NEVER:
                    item = "N";
                    break;

                case MINUTES_10:
                    item = "10M";
                    break;

                case HOUR_1:
                    item = "1H";
                    break;

                case DAY_1:
                    item = "1D";
                    break;

                case WEEK_1:
                    item = "1W";
                    break;

                case WEEK_2:
                    item = "2W";
                    break;

                case MONTH_1:
                    item = "1M";
                    break;

                default:
                    throw new Exception("Unsual paste expiration given");
            }
            fields.put("api_paste_expire_date", item);
        }
        if(pasteFormat != null)
        {
            fields.put("api_paste_format", pasteFormat.name());
        }
        if(userKey != null)
        {
            fields.put("api_user_key", userKey);
        }

        fields.put("api_option", "paste");

        HttpResponse<String> resp = Unirest.post("http://pastebin.com/api/api_post.php").fields(fields).asString();
        return resp.getBody();
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getPasteData() {
        return pasteData;
    }

    public void setPasteData(String pasteData) {
        this.pasteData = pasteData;
    }

    public PastePrivacy getPastePrivacy() {
        return pastePrivacy;
    }

    public void setPastePrivacy(PastePrivacy pastePrivacy) {
        this.pastePrivacy = pastePrivacy;
    }

    public String getPasteName() {
        return pasteName;
    }

    public void setPasteName(String pasteName) {
        this.pasteName = pasteName;
    }

    public PasteExpiration getPasteExpiration() {
        return pasteExpiration;
    }

    public void setPasteExpiration(PasteExpiration pasteExpiration) {
        this.pasteExpiration = pasteExpiration;
    }

    public PasteFormat getPasteFormat() {
        return pasteFormat;
    }

    public void setPasteFormat(PasteFormat pasteFormat) {
        this.pasteFormat = pasteFormat;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

}
