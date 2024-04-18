(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["pages/center/index"],
    {5736:function(n,i,t){},6269:function(n,i,t){"use strict";(function(n){t("1eb4");
    e(t("66fd"));var i=e(t("8250"));function e(n){return n&&n.__esModule?n:{default:n}}n(i.default)}).
        call(this,t("543d")["createPage"])},8250:function(n,i,t){"use strict";t.r(i);var e=t("dae1"),o=t("9563");for(var u in o)"default"!==u&&function(n){t.d(i,n,(function(){return o[n]}))}(u);t("bafc");var a,l=t("f0c5"),s=Object(l["a"])(o["default"],e["b"],e["c"],!1,null,null,null,!1,e["a"],a);i["default"]=s.exports},9563:function(n,i,t){"use strict";t.r(i);var e=t("c5a8"),o=t.n(e);for(var u in e)"default"!==u&&function(n){t.d(i,n,(function(){return e[n]}))}(u);i["default"]=o.a},bafc:function(n,i,t){"use strict";var e=t("5736"),o=t.n(e);o.a},c5a8:function(n,i,t){"use strict";(function(n){Object.defineProperty(i,"__esModule",{value:!0}),i.default=void 0;var t={data:function(){return{pic:"https://uviewui.com/common/logo.png",show:!0,userinfo:"",showuserreg:!1,liushuilist0:[],liushuilist1:[],txlist:[],bgyxkg:!1,showmsg:!1,blsuid:0,list:[{name:"积分记录"},{name:"好友助力"},{name:"我的用户"},{name:"提现记录"}],current:0,showhb:!1,hbmoney:.12,centerbnad:"",myteam:[],orderlist:[],xin:0}},onLoad:function(){var i=this,t=getApp();this.centerbnad=t.globalData.centerbnad,this.blsuid=t.globalData.blsuid,t.globalData.userinfo&&t.globalData.userinfo.nickname?(this.userinfo=t.globalData.userinfo,this.getliushui()):n.login({success:function(n){i.$u.post("reguser0",{code:n.code,bldailiid:t.globalData.bldailiid}).then((function(n){i.userinfo=n,t.globalData.userinfo=n,n.nickname||(i.showuserreg=!0)}))}})},onShow:function(){var i=this,t=getApp();this.bgyxkg=t.globalData.bgyxkg,!t.globalData.userinfo||t.globalData.userinfo.nickname,n.login({success:function(n){i.$u.post("reguser0",{code:n.code,bldailiid:t.globalData.bldailiid}).then((function(n){i.userinfo=n,t.globalData.userinfo=n,n.nickname||(i.showuserreg=!0)}))}})},methods:{closehb:function(){var n=getApp();this.showhb=!1;var i=this;i.$u.post("sxuserinfo",{uid:i.userinfo.id,bldailiid:n.globalData.bldailiid}).then((function(t){i.userinfo=t,n.globalData.userinfo=t}))},closehb0:function(){var n=this;getApp();n.showmsg=!1},gotixian:function(){var i=this,t=getApp();this.userinfo.money<100?n.showModal({showCancel:!1,title:"余额不足",content:"您的余额不足，满一元可以提现，无需审核"}):
                this.userinfo.txopenid?this.$u.post("usertixian",
                    {uid:i.userinfo.id,bldailiid:t.globalData.bldailiid}).then((function(e){400==e.code?
                    (i.$u.post("sxuserinfo",{uid:i.userinfo.id}).then
                    ((function(n){i.userinfo=n,t.globalData.userinfo=n})),
                        n.showModal({showCancel:!1,title:"提现失败",content:e.msg})):
                    (i.$u.post("sxuserinfo",
                        {uid:i.userinfo.id,bldailiid:t.globalData.bldailiid}).then
                    ((function(n){i.userinfo=n,t.globalData.userinfo=n})),
                        n.showModal({showCancel:!1,title:"提现成功",content:e.msg}))})):
                    wx.navigateToMiniProgram({appId:t.globalData.payid,
                        path:"pages/login/login?uid="+this.userinfo.id,success:function(n){}})},
                gosh:function(){n.switchTab({url:"/pages/heipt/index"})},
                gotixian1:function(){n.switchTab({url:"/pages/heipt/index"})},
                goshop:function(){n.navigateTo({url:"/pages/docinfo/shop"})},
                sectionChange:function(n){this.current=n},
                getuserinfo:function(i){var t=this,e=getApp();console.log(i);var o=i.detail.userInfo;
                n.login({success:function(n){var i=o;i.bldailiid=e.globalData.bldailiid,i.code=n.code,
                        t.$u.post("reguser",i).then((function(n){t.userinfo=n,e.globalData.userinfo=n,
                            t.showuserreg=!1,n.xin>0&&(t.xin=n.xin,t.showmsg=!0),
                            t.getliushui()}))}})},showad:function()
                {var i=getApp();if(!i.globalData.userinfo)return n.showToast({title:"请先授权登录"}),
                    void setTimeout((function(){n.switchTab({url:"/pages/center/index"})}),1500);
                this.videoAd&&this.videoAd.show().catch((function(){videoAd.load().then((function()
                {return videoAd.show()})).catch((function(i){n.showToast({title:"活动太火爆了,稍后再试~",icon:"none"})
                    ,console.log("激励视频 广告显示失败")}))}))},
                kfzx:function(){var i=getApp();
                this.$u.post("getkftel",
                    {bldailiid:i.globalData.bldailiid}).then((function(i){n.makePhoneCall({phoneNumber:""+i})}))},
                getliushui:function(){var n=this,i=getApp();
                this.$u.post("getliushui",
                    {uid:n.userinfo.id,bldailiid:i.globalData.bldailiid}).then((function(i){console.log(i),n.liushuilist0=i.liushuilist0,n.liushuilist1=i.liushuilist1,n.txlist=i.txlist,n.myteam=i.myteam,n.orderlist=i.orderlist}))}}};i.default=t}).call(this,t("543d")["default"])},
        dae1:function(n,i,t){"use strict";t.d(i,"b",(function(){return o})),t.d(i,"c",(function(){return u})),t.d(i,"a",(function(){return e}));
                    var e={uAvatar:function(){return t.e("uview-ui/components/u-avatar/u-avatar").then(t.bind(null,"63c6"))},uIcon:function(){return t.e("uview-ui/components/u-icon/u-icon").then(t.bind(null,"16d4"))},
                uButton:function(){return t.e("uview-ui/components/u-button/u-button").then(t.bind(null,"55df"))},uSubsection:function(){return t.e("uview-ui/components/u-subsection/u-subsection").then(t.bind(null,"f6d6"))},uCellGroup:function(){return t.e("uview-ui/components/u-cell-group/u-cell-group").then(t.bind(null,"9bd2"))},
                uCellItem:function(){return t.e("uview-ui/components/u-cell-item/u-cell-item").then(t.bind(null,"0769"))},
                uTag:function(){return t.e("uview-ui/components/u-tag/u-tag").then(t.bind(null,"e242"))},
                uEmpty:function(){return t.e("uview-ui/components/u-empty/u-empty").then(t.bind(null,"5a9f"))},
                uPopup:function(){return t.e("uview-ui/components/u-popup/u-popup").then(t.bind(null,"78b1"))},
                uTabbar:function(){return Promise.all([t.e("common/vendor"),t.e("uview-ui/components/u-tabbar/u-tabbar")]).then(t.bind(null,"6385"))}},
        o=function(){var n=this,i=n.$createElement;n._self._c},u=[]}},
    [["6269","common/runtime","common/vendor"]]]);