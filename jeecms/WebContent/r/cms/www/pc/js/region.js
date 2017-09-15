  // 填充省份
        var provincesData = "|请选择,110000|北京市,120000|天津市,130000|河北省,140000|山西省,150000|内蒙古自治区,210000|辽宁省,220000|吉林省,230000|黑龙江省,310000|上海市,320000|江苏省,330000|浙江省,340000|安徽省,350000|福建省,360000|江西省,370000|山东省,410000|河南省,420000|湖北省,430000|湖南省,440000|广东省,450000|广西藏族自治区,460000|海南省,500000|重庆市,510000|四川省,520000|贵州省,530000|云南省,540000|西藏自治区,610000|陕西省,620000|甘肃省,630000|青海省,640000|宁夏回族自治区,650000|新疆维吾尔自治区,710000|台湾省,810000|香港特别行政区,820000|澳门特别行政区";
        function getProvinces(selProvance,checkedProvance)
        {
            selProvance.options.length = 0;
            var flag = false;
            var pAs = provincesData.split(",");
            for(var pA in pAs)
            {
                var pA_parts = pAs[pA].split("|");
                selProvance.options.add(new Option(pA_parts[1],pA_parts[0]));
                if(checkedProvance == pA_parts[0])
                {
                	if(selProvance.options[pA]){
                		selProvance.options[pA].selected = true;
                	} else {
                		selProvance.options[0].selected = true;
                	}
                    flag = true;
                }
            }
           
            if(selProvance.options.length == 0)
            {
                selProvance.disabled = true;
                selProvance.options.add(new Option("","000000"));
            }
            else
            {
                selProvance.disabled = false;
            }
            if(!flag){
                selProvance.options[0].selected = true;
            }
        }
       
        var citysData = "|请选择,110101|东城区,110102|西城区,110103|崇文区,110104|宣武区,110105|朝阳区,110106|丰台区,110107|石景山区,110108|海淀区,110109|门头沟区,110111|房山区,110112|通州区,110113|顺义区,110114|昌平区,110115|大兴区,110116|怀柔区,110117|平谷区,110228|密云县,110229|延庆县,120101|和平区,120102|河东区,120103|河西区,120104|南开区,120105|河北区,120106|红桥区,120107|塘沽区,120108|汉沽区,120109|大港区,120110|东丽区,120111|西青区,120112|津南区,120113|北辰区,120114|武清区,120115|宝坻区,120221|宁河县,120223|静海县,120225|蓟县,130100|石家庄市,130200|唐山市,130300|秦皇岛市,130400|邯郸市,130500|邢台市,130600|保定市,130700|张家口市,130800|承德市,130900|沧州市,131000|廊坊市,131100|衡水市,140100|太原市,140200|大同市,140300|阳泉市,140400|长治市,140500|晋城市,140600|朔州市,140700|晋中市,140800|运城市,140900|忻州市,141000|临汾市,142300|吕梁地区,150100|呼和浩特市,150200|包头市,150300|乌海市,150400|赤峰市,150500|通辽市,150600|鄂尔多斯市,150700|呼伦贝尔市,152200|兴安盟,152500|锡林郭勒盟,152600|乌兰察布盟,152700|巴彦淖尔盟,152900|阿拉善盟,210100|沈阳市,210200|大连市,210300|鞍山市,210400|抚顺市,210500|本溪市,210600|丹东市,210700|锦州市,210800|营口市,210900|阜新市,211000|辽阳市,211100|盘锦市,211200|铁岭市,211300|朝阳市,211400|葫芦岛市,220100|长春市,220200|吉林市,220300|四平市,220400|辽源市,220500|通化市,220600|白山市,220700|松原市,220800|白城市,222400|延边州,230100|哈尔滨市,230200|齐齐哈尔市,230300|鸡西市,230400|鹤岗市,230500|双鸭山市,230600|大庆市,230700|伊春市,230800|佳木斯市,230900|七台河市,231000|牡丹江市,231100|黑河市,231200|绥化市,232700|大兴安岭地区,310101|黄浦区,310103|卢湾区,310104|徐汇区,310105|长宁区,310106|静安区,310107|普陀区,310108|闸北区,310109|虹口区,310110|杨浦区,310112|闵行区,310113|宝山区,310114|嘉定区,310115|浦东新区,310116|金山区,310117|松江区,310118|青浦区,310119|南汇区,310120|奉贤区,310230|崇明县,320100|南京市,320200|无锡市,320300|徐州市,320400|常州市,320500|苏州市,320600|南通市,320700|连云港市,320800|淮安市,320900|盐城市,321000|扬州市,321100|镇江市,321200|泰州市,321300|宿迁市,330100|杭州市,330200|宁波市,330300|温州市,330400|嘉兴市,330500|湖州市,330600|绍兴市,330700|金华市,330800|衢州市,330900|舟山市,331000|台州市,331100|丽水市,340100|合肥市,340200|芜湖市,340300|蚌埠市,340400|淮南市,340500|马鞍山市,340600|淮北市,340700|铜陵市,340800|安庆市,341000|黄山市,341100|滁州市,341200|阜阳市,341300|宿州市,341400|巢湖市,341500|六安市,341600|亳州市,341700|池州市,341800|宣城市,350100|福州市,350200|厦门市,350300|莆田市,350400|三明市,350500|泉州市,350600|漳州市,350700|南平市,350800|龙岩市,350900|宁德市,360100|南昌市,360200|景德镇市,360300|萍乡市,360400|九江市,360500|新余市,360600|鹰潭市,360700|赣州市,360800|吉安市,360900|宜春市,361000|抚州市,361100|上饶市,370100|济南市,370200|青岛市,370300|淄博市,370400|枣庄市,370500|东营市,370600|烟台市,370700|潍坊市,370800|济宁市,370900|泰安市,371000|威海市,371100|日照市,371200|莱芜市,371300|临沂市,371400|德州市,371500|聊城市,371600|滨州市,371700|菏泽市,410100|郑州市,410200|开封市,410300|洛阳市,410400|平顶山市,410500|安阳市,410600|鹤壁市,410700|新乡市,410800|焦作市,410881|济源市,410900|濮阳市,411000|许昌市,411100|漯河市,411200|三门峡市,411300|南阳市,411400|商丘市,411500|信阳市,411600|周口市,411700|驻马店市,420100|武汉市,420200|黄石市,420300|十堰市,420500|宜昌市,420600|襄樊市,420700|鄂州市,420800|荆门市,420900|孝感市,421000|荆州市,421100|黄冈市,421200|咸宁市,421300|随州市,422800|恩施州,429004|仙桃市,429005|潜江市,429006|天门市,429021|神农架林区,430100|长沙市,430200|株洲市,430300|湘潭市,430400|衡阳市,430500|邵阳市,430600|岳阳市,430700|常德市,430800|张家界市,430900|益阳市,431000|郴州市,431100|永州市,431200|怀化市,431300|娄底市,433100|湘西土家族苗族自治州,440100|广州市,440200|韶关市,440300|深圳市,440400|珠海市,440500|汕头市,440600|佛山市,440700|江门市,440800|湛江市,440900|茂名市,441200|肇庆市,441300|惠州市,441400|梅州市,441500|汕尾市,441600|河源市,441700|阳江市,441800|清远市,441900|东莞市,442000|中山市,445100|潮州市,445200|揭阳市,445300|云浮市,450100|南宁市,450200|柳州市,450300|桂林市,450400|梧州市,450500|北海市,450600|防城港市,450700|钦州市,450800|贵港市,450900|玉林市,451000|百色市,451100|贺州市,451200|河池市,451300|来宾市,451400|崇左市,460100|海口市,460200|三亚市,469001|五指山市,469002|琼海市,469003|儋州市,469005|文昌市,469006|万宁市,469007|东方市,469025|定安县,469026|屯昌县,469027|澄迈县,469028|临高县,469030|白沙黎族自治县,469031|昌江黎族自治县,469033|乐东黎族自治县,469034|陵水黎族自治县,469035|保亭黎族苗族自治县,469036|琼中黎族苗族自治县,469037|西沙群岛,469038|南沙群岛,469039|中沙群岛的岛礁及其海域,500101|万州区,500102|涪陵区,500103|渝中区,500104|大渡口区,500105|江北区,500106|沙坪坝区,500107|九龙坡区,500108|南岸区,500109|北碚区,500110|万盛区,500111|双桥区,500112|渝北区,500113|巴南区,500114|黔江区,500115|长寿区,500222|綦江县,500223|潼南县,500224|铜梁县,500225|大足县,500226|荣昌县,500227|璧山县,500228|梁平县,500229|城口县,500230|丰都县,500231|垫江县,500232|武隆县,500233|忠县,500234|开县,500235|云阳县,500236|奉节县,500237|巫山县,500238|巫溪县,500240|石柱土家族自治县,500241|秀山土家族苗族自治县,500242|酉阳土家族苗族自治县,500243|彭水苗族土家族自治县,500381|江津市,500382|合川市,500383|永川市,500384|南川市,510100|成都市,510300|自贡市,510400|攀枝花市,510500|泸州市,510600|德阳市,510700|绵阳市,510800|广元市,510900|遂宁市,511000|内江市,511100|乐山市,511300|南充市,511400|眉山市,511500|宜宾市,511600|广安市,511700|达州市,511800|雅安市,511900|巴中市,512000|资阳市,513200|阿坝藏族羌族自治州,513300|甘孜藏族自治州,513400|凉山彝族自治州,520100|贵阳市,520200|六盘水市,520300|遵义市,520400|安顺市,522200|铜仁地区,522300|黔西南布依族苗族自治州,522400|毕节地区,522600|黔东南苗族侗族自治州,522700|黔南布依族苗族自治州,530100|昆明市,530300|曲靖市,530400|玉溪市,530500|保山市,530600|昭通市,530700|丽江市,532300|楚雄彝族自治州,532500|红河哈尼族彝族自治州,532600|文山州,532700|思茅地区,532800|西双版纳傣族自治州,532900|大理白族自治州,533100|德宏傣族景颇族自治州,533300|怒江傈僳族自治州,533400|迪庆州,533500|临沧地区,540100|拉萨市,542100|昌都地区,542200|山南地区,542300|日喀则地区,542400|那曲地区,542500|阿里地区,542600|林芝地区,610100|西安市,610200|铜川市,610300|宝鸡市,610400|咸阳市,610500|渭南市,610600|延安市,610700|汉中市,610800|榆林市,610900|安康市,611000|商洛市,620100|兰州市,620200|嘉峪关市,620300|金昌市,620400|白银市,620500|天水市,620600|武威市,620700|张掖市,620800|平凉市,620900|酒泉市,621000|庆阳市,622400|定西地区,622600|陇南地区,622900|临夏回族自治州,623000|甘南藏族自治州,630100|西宁市,632100|海东地区,632200|海北藏族自治州,632300|黄南藏族自治州,632500|海南藏族自治州,632600|果洛藏族自治州,632700|玉树州,632800|海西州,640100|银川市,640200|石嘴山市,640300|吴忠市,640400|固原市,640500|中卫市,650100|乌鲁木齐市,650200|克拉玛依市,652100|吐鲁番地区,652200|哈密地区,652300|昌吉回族自治州,652700|博尔塔拉蒙古自治州,652800|巴音郭楞蒙古自治州,652900|阿克苏地区,653000|克孜勒苏柯尔克孜自治州,653100|喀什地区,653200|和田地区,654000|伊犁哈萨克自治州,654200|塔城地区,654300|阿勒泰地区,659001|石河子市,659002|阿拉尔市,659003|图木舒克市,659004|五家渠市,710100|台北市,710200|高雄市,710300|基隆市,710400|台中市,710500|台南市,710600|新竹市,710700|嘉义市,710801|台北县(板桥市),710802|宜兰县(宜兰市),710803|新竹县(竹北市),710804|桃园县(桃园市),710805|苗栗县(苗栗市),710806|台中县(丰原市),710807|彰化县(彰化市),710808|南投县(南投市),710809|嘉义县(太保市),710810|云林县(斗六市),710811|台南县(新营市),710812|高雄县(凤山市),710813|屏东县(屏东市),710814|台东县(台东市),710815|花莲县(花莲市),710816|澎湖县(马公市),810001|中西区,810002|东区,810003|九龙城区,810004|观塘区,810005|南区,810006|深水埗区,810007|黄大仙区,810008|湾仔区,810009|油尖旺区,810010|离岛区,810011|葵青区,810012|北区,810013|西贡区,810014|沙田区,810015|屯门区,810016|大埔区,810017|荃湾区,810018|元朗区,820001|花地玛堂区(北区),820002|圣安多尼堂区(花王堂区),820003|大堂区(中区),820004|望德堂区,820005|风顺堂区(圣老愣佐堂区),820006|嘉模堂区(氹仔),820007|圣方济各堂区(路环),820008|氹仔城";
        function getCitys(selCity,pv,checkedCity)
        {
            var flag = false;
            selCity.options.length = 0;
           
            var cAs = citysData.split(",");
            for(var cA in cAs)
            {
                var cA_parts = cAs[cA].split("|");
               
                if(pv.substring(0,2) == cA_parts[0].substring(0,2))
                {
                    selCity.options.add(new Option(cA_parts[1],cA_parts[0]));
                    if(checkedCity == cA_parts[0])
                    {
                    	if (selCity.options[cA]){
                    		selCity.options[cA].selected = true;
                    	} else {
                    		selCity.options[0].selected = true;
                    	}
                        flag = true;
                    }
                }
            }
           
            if(selCity.options.length == 0)
            {
                selCity.disabled = true;
                selCity.options.add(new Option("","000000"));
            }
            else
            {
                 selCity.disabled = false;
            }
            if(!flag){
                selCity.options[0].selected = true;
            }
        }
       
        function loadData(selProvance,checkedProvance,selCity,checkedCity)
        {
            getProvinces(selProvance,checkedProvance);
            getCitys(selCity,selProvance.options[selProvance.selectedIndex].value,checkedCity);
        }
        
        function chgProvinces(selProvance,selCity)
        {
            getCitys(selCity,selProvance.options[selProvance.selectedIndex].value);
			var p1 = selProvance.value;
			var p2 = selCity.value;
        }