package club.mokeblog.diary.model.domain;

import java.util.List;

public class Picture {

    /**
     * msg : success
     * res : {"vertical":[{"preview":"http://img5.adesk.com/5fac9ae7e7bce775ee1398fd","thumb":"http://img5.adesk.com/5fac9ae7e7bce775ee1398fd?imageMogr2/thumbnail/!350x540r/gravity/Center/crop/350x540","img":"http://img5.adesk.com/5fac9ae7e7bce775ee1398fd?imageMogr2/thumbnail/!720x1280r/gravity/Center/crop/720x1280","views":0,"cid":["4e4d610cdf714d2966000003"],"rule":"?imageMogr2/thumbnail/!$<Width>x$<Height>r/gravity/Center/crop/$<Width>x$<Height>","ncos":1,"rank":198,"source_type":"vertical","tag":[],"url":[],"wp":"http://img5.adesk.com/5fac9ae7e7bce775ee1398fd","xr":false,"cr":false,"favs":67,"atime":1.606878304E9,"id":"5fac9ae7e7bce775ee1398fd","store":"qiniu","desc":""},{"preview":"http://img5.adesk.com/5fad0ae1e7bce775da9e3b56","thumb":"http://img5.adesk.com/5fad0ae1e7bce775da9e3b56?imageMogr2/thumbnail/!350x540r/gravity/Center/crop/350x540","img":"http://img5.adesk.com/5fad0ae1e7bce775da9e3b56?imageMogr2/thumbnail/!720x1280r/gravity/Center/crop/720x1280","views":0,"cid":["4e4d610cdf714d2966000003"],"rule":"?imageMogr2/thumbnail/!$<Width>x$<Height>r/gravity/Center/crop/$<Width>x$<Height>","ncos":0,"rank":374,"source_type":"vertical","tag":[],"url":[],"wp":"http://img5.adesk.com/5fad0ae1e7bce775da9e3b56","xr":false,"cr":false,"favs":67,"atime":1.606917911E9,"id":"5fad0ae1e7bce775da9e3b56","store":"qiniu","desc":""},{"preview":"http://img5.adesk.com/5fb5f84b0422087511888456","thumb":"http://img5.adesk.com/5fb5f84b0422087511888456?imageMogr2/thumbnail/!350x540r/gravity/Center/crop/350x540","img":"http://img5.adesk.com/5fb5f84b0422087511888456?imageMogr2/thumbnail/!720x1280r/gravity/Center/crop/720x1280","views":0,"cid":["4e4d610cdf714d2966000003"],"rule":"?imageMogr2/thumbnail/!$<Width>x$<Height>r/gravity/Center/crop/$<Width>x$<Height>","ncos":2,"rank":290,"source_type":"vertical","tag":[],"url":[],"wp":"http://img5.adesk.com/5fb5f84b0422087511888456","xr":false,"cr":false,"favs":70,"atime":1.605891541E9,"id":"5fb5f84b0422087511888456","store":"qiniu","desc":""},{"preview":"http://img5.adesk.com/5fb5f9c90422087511888513","thumb":"http://img5.adesk.com/5fb5f9c90422087511888513?imageMogr2/thumbnail/!350x540r/gravity/Center/crop/350x540","img":"http://img5.adesk.com/5fb5f9c90422087511888513?imageMogr2/thumbnail/!720x1280r/gravity/Center/crop/720x1280","views":0,"cid":["4e4d610cdf714d2966000000"],"rule":"?imageMogr2/thumbnail/!$<Width>x$<Height>r/gravity/Center/crop/$<Width>x$<Height>","ncos":1,"rank":475,"source_type":"vertical","tag":[],"url":[],"wp":"http://img5.adesk.com/5fb5f9c90422087511888513","xr":false,"cr":false,"favs":98,"atime":1.606745111E9,"id":"5fb5f9c90422087511888513","store":"qiniu","desc":""},{"preview":"http://img5.adesk.com/5fb94df431f613465847b27f","thumb":"http://img5.adesk.com/5fb94df431f613465847b27f?imageMogr2/thumbnail/!350x540r/gravity/Center/crop/350x540","img":"http://img5.adesk.com/5fb94df431f613465847b27f?imageMogr2/thumbnail/!720x1280r/gravity/Center/crop/720x1280","views":0,"cid":["4e4d610cdf714d2966000003"],"rule":"?imageMogr2/thumbnail/!$<Width>x$<Height>r/gravity/Center/crop/$<Width>x$<Height>","ncos":0,"rank":222,"source_type":"vertical","tag":[],"url":[],"wp":"http://img5.adesk.com/5fb94df431f613465847b27f","xr":false,"cr":false,"favs":50,"atime":1.606182661E9,"id":"5fb94df431f613465847b27f","store":"qiniu","desc":""}]}
     * code : 0
     */

    private String msg;
    private ResBean res;
    private int code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResBean getRes() {
        return res;
    }

    public void setRes(ResBean res) {
        this.res = res;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class ResBean {
        private List<VerticalBean> vertical;

        public List<VerticalBean> getVertical() {
            return vertical;
        }

        public void setVertical(List<VerticalBean> vertical) {
            this.vertical = vertical;
        }

        public static class VerticalBean {
            /**
             * preview : http://img5.adesk.com/5fac9ae7e7bce775ee1398fd
             * thumb : http://img5.adesk.com/5fac9ae7e7bce775ee1398fd?imageMogr2/thumbnail/!350x540r/gravity/Center/crop/350x540
             * img : http://img5.adesk.com/5fac9ae7e7bce775ee1398fd?imageMogr2/thumbnail/!720x1280r/gravity/Center/crop/720x1280
             * views : 0
             * cid : ["4e4d610cdf714d2966000003"]
             * rule : ?imageMogr2/thumbnail/!$<Width>x$<Height>r/gravity/Center/crop/$<Width>x$<Height>
             * ncos : 1
             * rank : 198
             * source_type : vertical
             * tag : []
             * url : []
             * wp : http://img5.adesk.com/5fac9ae7e7bce775ee1398fd
             * xr : false
             * cr : false
             * favs : 67
             * atime : 1.606878304E9
             * id : 5fac9ae7e7bce775ee1398fd
             * store : qiniu
             * desc : 
             */

            private String preview;
            private String thumb;
            private String img;
            private int views;
            private String rule;
            private int ncos;
            private int rank;
            private String source_type;
            private String wp;
            private boolean xr;
            private boolean cr;
            private int favs;
            private double atime;
            private String id;
            private String store;
            private String desc;
            private List<String> cid;
            private List<?> tag;
            private List<?> url;

            public String getPreview() {
                return preview;
            }

            public void setPreview(String preview) {
                this.preview = preview;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public int getViews() {
                return views;
            }

            public void setViews(int views) {
                this.views = views;
            }

            public String getRule() {
                return rule;
            }

            public void setRule(String rule) {
                this.rule = rule;
            }

            public int getNcos() {
                return ncos;
            }

            public void setNcos(int ncos) {
                this.ncos = ncos;
            }

            public int getRank() {
                return rank;
            }

            public void setRank(int rank) {
                this.rank = rank;
            }

            public String getSource_type() {
                return source_type;
            }

            public void setSource_type(String source_type) {
                this.source_type = source_type;
            }

            public String getWp() {
                return wp;
            }

            public void setWp(String wp) {
                this.wp = wp;
            }

            public boolean isXr() {
                return xr;
            }

            public void setXr(boolean xr) {
                this.xr = xr;
            }

            public boolean isCr() {
                return cr;
            }

            public void setCr(boolean cr) {
                this.cr = cr;
            }

            public int getFavs() {
                return favs;
            }

            public void setFavs(int favs) {
                this.favs = favs;
            }

            public double getAtime() {
                return atime;
            }

            public void setAtime(double atime) {
                this.atime = atime;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getStore() {
                return store;
            }

            public void setStore(String store) {
                this.store = store;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public List<String> getCid() {
                return cid;
            }

            public void setCid(List<String> cid) {
                this.cid = cid;
            }

            public List<?> getTag() {
                return tag;
            }

            public void setTag(List<?> tag) {
                this.tag = tag;
            }

            public List<?> getUrl() {
                return url;
            }

            public void setUrl(List<?> url) {
                this.url = url;
            }
        }
    }
}

