package com.example;
/**
 * @author Administrator
 * @version $Rev$
 * @time 2016/5/16 15:55
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class SystemNotify implements Comparable<SystemNotify>{
    private static final String TAG = SystemNotify.class.getSimpleName();
    public int id;
    public String icon;
    public String app_id;
    public long t;
    public String body;
    public boolean isNew;

    //以下内容根据body解析
    public String uid;
    public String uname;
    public String action;
    public String actionId;
    public String actionName;

    public String content;
    public String time;
    public int read = 0 ;// 0未读,1已读

    private String TAG_UID_START = "[uid=";
    private String TAG_UID_END = "]";
    private String TAG_UNAME_END = "[/uid]";
    private String TAG_ACTIONID_START = "[vid=";
    private String TAG_ACTIONNAME_END = "[/vid]";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public long getT() {
        return t;
    }

    public void setT(long t) {
        this.t = t;
//        time = TimeTools.getTimeText(t);
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getActionId() {
        return actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getRead() {
        return read;
    }

    public void setRead(int read) {
        this.read = read;
    }

    public void parse(){
        if(body==null) return;

        //解析uid uname
        int usi = body.indexOf(TAG_UID_START);
        int uei = body.indexOf(TAG_UID_END,usi);
        int nei = body.indexOf(TAG_UNAME_END);
        if (usi != -1 && uei != -1 && nei != -1) {
            try{
                //解析uname
                uid = body.substring(usi+TAG_UID_START.length(),uei);
                Log.i("NotifyParse","uid:"+uid+",usi:"+usi+",uei:"+uei);
                //解析uname
                uname = body.substring(uei+1,nei);
                Log.i("NotifyParse","uname:"+uname+",nei:"+nei);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        int actionStartIndex = 0;
        if (nei != -1) {
            actionStartIndex = nei + TAG_UNAME_END.length();
        }

        //解析action
        int asi = body.indexOf(TAG_ACTIONID_START);
        int ansi = body.indexOf(TAG_ACTIONNAME_END);
        if (asi != -1 && ansi != -1) {
            try {
                action = body.substring(actionStartIndex,asi);
                Log.i("NotifyParse","action:"+action);

                //解析actionId
                int aiei = body.indexOf(TAG_UID_END,asi);
                actionId = body.substring(asi+TAG_ACTIONID_START.length(),aiei);
                Log.i("NotifyParse","actionId:"+actionId);

                //解析actionname
                actionName = body.substring(aiei+1,ansi);
                Log.i("NotifyParse","actionName:"+actionName);

                //解析content
                content = body.substring(ansi+TAG_ACTIONNAME_END.length());
                Log.i("NotifyParse","content:"+content);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } else {
            try {
                //解析content
                content = body.substring(actionStartIndex);
                Log.i("NotifyParse","content:"+content);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

//        time = TimeTools.getTimeText(t);
        Log.i("NotifyParse","time:"+time+",t:"+t);

        Log.i(TAG,this.toString());
    }

    @Override
    public String toString() {
        return "id:"+id+",icon:"+icon+",app_id:"+app_id+",t:"+t+"," +
                "uid:"+uid+",uname:"+uname+",action:"+action
                +"actionId:"+actionId+",actionName:"+actionName+",content:"+content
                +",time:"+time;
    }

    @Override
    public int compareTo(SystemNotify another) {
        return t - another.t > 0 ? -1 : 1;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
}
