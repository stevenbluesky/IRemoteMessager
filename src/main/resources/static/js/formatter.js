function formatter_platform(value, row, index) {
    if(value==undefined){
        value=row.platform;
    }
    if (value == 999999) {
        return "默认";
    }else if (value == 0) {
        return "经纬纵横";
    }
    else if (value == 2) {
        return "创佳";
    }
    else if (value == 3) {
        return "多灵";
    }
    else if (value == 4) {
        return "Tecus";
    }
    else if (value == 6) {
        return "小虎智慧家";
    }
    else if (value == 7) {
        return "iSurpass";
    }
    else if (value == 8) {
        return "Keemple";
    }
    else if (value == 9) {
        return "Ameta";
    }
    else if (value == 10) {
        return "金网智能";
    }
    else if (value == 11) {
        return "乾坤小智";
    }
    else if (value == 12) {
        return "欧多客";
    }
    else if (value == 13) {
        return "讯宿智能锁";
    }
    else if (value == 14) {
        return "西屋安防 A";
    }
}
function formatter_type(value, row, index) {
    if (value == 1) {
        return "附加字段";
    }
    else if (value == 2) {
        return "单句";
    }
    else if (value == 3) {
        return "邮件标题";
    }
    else if (value == 4) {
        return "邮件内容";
    }
}
function formatter_language(value, row, index) {
    if (value == "zh_CN") {
        return "中文(zh_CN)";
    }
    else if (value == "zh_HK") {
        return "繁体(zh_HK)";
    }
    else if (value == "en_US") {
        return "英文(en_US)";
    }
    else if (value == "fr_CA") {
        return "法语(fr_CA)";
    }else if(value == "vi_VN"){
        return "越南语(vi_VN)";
    }
}


var platform = [
    {platformName:'默认',platformValue:'999999'},
    {platformName:'经纬纵横',platformValue:'0'},
    {platformName:'创佳',platformValue:'2'},
    {platformName:'多灵',platformValue:'3'},
    {platformName:'Tecus',platformValue:'4'},
    {platformName:'小虎智慧家',platformValue:'6'},
    {platformName:'iSurpass',platformValue:'7'},
    {platformName:'Keemple',platformValue:'8'},
    {platformName:'Ameta',platformValue:'9'},
    {platformName:'金网智能',platformValue:'10'},
    {platformName:'乾坤小智',platformValue:'11'},
    {platformName:'欧多客',platformValue:'12'},
    {platformName:'讯宿智能锁',platformValue:'13'},
    {platformName:'西屋安防 A',platformValue:'14'}]

var language = [
    {languageName:'中文(zh_CN)',languageValue:'zh_CN'},
    {languageName:'繁体(zh_HK)',languageValue:'ch_HK'},
    {languageName:'英文(en_US)',languageValue:'en_US'},
    {languageName:'法语(fr_CA)',languageValue:'fr_CA'},
    {languageName:'越南语(vi_VN)',languageValue:'vi_VN'}]

var type = [
    {typeName:'推送附加字段',typeValue:'1'},
    {typeName:'单句',typeValue:'2'},
    {typeName:'邮件标题',typeValue:'3'},
    {typeName:'邮件内容',typeValue:'4'}]