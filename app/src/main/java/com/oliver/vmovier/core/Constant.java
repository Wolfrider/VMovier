package com.oliver.vmovier.core;

public final class Constant {

    public static long EXIT_INTERVAL_TIME = 2000;

    public static class IntentAction {

        public static final String HOME_ACTION = "vmovier.home";

        public static final String ARTICLE_ACTION = "vmovier.article";

        public static final String WEBVIEW_ACTION = "vmovier.webview";

        public static final String DAILY_COVER_ACTION = "vmovier.dailycover";

        public static final String CHANNEL_LIST_ACTION = "vmovier.channellist";

        public static final String VIDEO_ACTION = "vmovier.video";

        public static final String PARAM_CONTENT = "CONTENT";

        public static final String PARAM_TYPE = "TYPE";

        public static final String PARAM_NAV = "NAV";
    }

    public static class Local {

        public static final String DIR_NAME = "local";

        public static final String FIRST_SCREEN_JSON_NAME = "first_screen.json";

    }

    public static final class SCHEME {

        public static final String SEARCH = "vmovier://search";
    }

    public static class BootLoaderType {

        public static final int SPLASH = 0x01;

        public static final int HOME = 0x02;

        public static final int SEARCH = 0x03;

    }

    public static class NavType {

        public static final int WEBVIEW = 1;

        public static final int ARTICLE = 2;

        public static final int DAILY_COVER = 3;

        public static final int CHANNEL_LIST = 4;

        public static final int VIDEO = 5;

        public static final int SCHEME = 6;
    }

    public static class CateType {

        public static final int CATE = 0x00;

        public static final int TAB = 0x01;

        public static final int TAG = 0x02;

        public static final int LINK = 0x02;
    }

    public static class CardType {

        public static final int CHANNEL = 0x0000;

        public static final int POST = 0x0001;

        public static final int RECOMMEND_WORD = 0x0002;

        public static final int FILTER = 0x0003;

        public static class Discovery {
            public static final int BANNER = 0x0100;

            public static final int TITLE = 0x0102;

            public static final int ALBUM = 0x0103;

            public static final int GRID_POST = 0x0104;
        }

        public static class VideoDetail {

            public static final int CONTENT = 0x0200;

            public static final int INTRO = 0x0201;

            public static final int RELATED = 0x0202;

            public static final int COMMENT = 0x0203;
        }

        public static class ArticleDetail {

            public static final int HEADER = 0x0300;

            public static final int FOOTER = 0x0301;

            public static final int TEXT = 0x0302;

            public static final int IMAGE = 0x0303;
        }
    }
}
