package gson学习与反射;

import com.google.gson.annotations.SerializedName;

public class JsonA {

    /**
     * section : {"music":{"sign":"music","name":"首页"}}
     * section2 : {"music":{"sign":"music2","name":"首页"}}
     */

    @SerializedName("section")
    public SectionBean section;
    @SerializedName("section2")
    public Section2Bean section2;

    public static class SectionBean {
        /**
         * music : {"sign":"music","name":"首页"}
         */

        @SerializedName("music")
        public MusicBean music;

        public static class MusicBean {
            /**
             * sign : music
             * name : 首页
             */

            @SerializedName("sign")
            public String sign;
            @SerializedName("name")
            public String name;
        }
    }

    public static class Section2Bean {
        /**
         * music : {"sign":"music2","name":"首页"}
         */

        @SerializedName("music")
        public MusicBean music;

        public static class MusicBean {
            /**
             * sign : music2
             * name : 首页
             */

            @SerializedName("sign")
            public String sign;
            @SerializedName("name")
            public String name;
        }
    }
}
