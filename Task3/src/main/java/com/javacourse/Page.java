package com.javacourse;

public class Page {
    private String id;
    private String title;
    private String type;
    private boolean doNeedAuthorize;
    private boolean isFree;
    private boolean hasEmail;
    private boolean isDownloadable;

    public static final String xmlNodeName = "page";

    private Page(PageBuilder pageBuilder) {
        this.id = pageBuilder.id;
        this.title = pageBuilder.title;
        this.type = pageBuilder.type;
        this.doNeedAuthorize = pageBuilder.doNeedAuthorize;
        this.hasEmail = pageBuilder.hasEmail;
        this.isFree = pageBuilder.isFree;
        this.isDownloadable = pageBuilder.isDownloadable;
    }

    public static class PageBuilder{
        private String id;
        private String title;
        private String type;
        private boolean doNeedAuthorize;
        private boolean isFree;
        private boolean hasEmail;
        private boolean isDownloadable;

        public PageBuilder(String id) {
            this.id = id;
        }

        public PageBuilder title(String title){
            this.title = title;
            return this;
        }

        public PageBuilder type(String type){
            this.type = type;
            return this;
        }

        public PageBuilder doNeedAuthorize(boolean doNeedAuthorize){
            this.doNeedAuthorize = doNeedAuthorize;
            return this;
        }

        public PageBuilder isFree(boolean isFree){
            this.isFree = isFree;
            return this;
        }

        public PageBuilder hasEmail(boolean hasEmail){
            this.hasEmail = hasEmail;
            return this;
        }

        public PageBuilder isDownloadable(boolean isDownloadable){
            this.isDownloadable = isDownloadable;
            return this;
        }

        public Page build(){
            return new Page(this);
        }

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isDoNeedAuthorize() {
        return doNeedAuthorize;
    }

    public void setDoNeedAuthorize(boolean doNeedAuthorize) {
        this.doNeedAuthorize = doNeedAuthorize;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public boolean isHasEmail() {
        return hasEmail;
    }

    public void setHasEmail(boolean hasEmail) {
        this.hasEmail = hasEmail;
    }

    public boolean isDownloadable() {
        return isDownloadable;
    }

    public void setDownloadable(boolean downloadable) {
        isDownloadable = downloadable;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Page{");
        sb.append("id='").append(id).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", doNeedAuthorize=").append(doNeedAuthorize);
        sb.append(", isFree=").append(isFree);
        sb.append(", hasEmail=").append(hasEmail);
        sb.append(", isDownloadable=").append(isDownloadable);
        sb.append('}');
        return sb.toString();
    }
}
