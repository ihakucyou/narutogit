package glodon.gbmp.xm.jsgc.model;



/**
 * 
 * 建设云数据字典枚举(招标类型)
 * @author zhangyb-f
 * @since jdk1.7
 * @date 2021年12月22号
 *  
 */

public enum TenderClassNumEnum{

	KC(){
		@Override
		public String getDDValue() {
			return "001";
		}
	},
	SJ(){
		@Override
		public String getDDValue() {
			return "002";
		}
	},
	SG(){
		@Override
		public String getDDValue() {
			return "003";
		}
	},
	JL(){
		@Override
		public String getDDValue() {
			return "004";
		}
	},
	GCZCB(){
		@Override
		public String getDDValue() {
			return "006";
		}
	},
	XMGL(){
		@Override
		public String getDDValue() {
			return "0027";
		}
	},
	QGCGCZX(){
		@Override
		public String getDDValue() {
			return "010";
		}
	},
	QT(){
		@Override
		public String getDDValue() {
			return "011";
		}
	};
	public abstract String getDDValue();
	    
	public static String getInstance(String enumValue) {
        for (TenderClassNumEnum dataEnum : TenderClassNumEnum.values()) {
            if (enumValue.equals(dataEnum.toString())) {
                return dataEnum.getDDValue();
            }
        }
        return TenderClassNumEnum.QT.getDDValue();
    }
}
