package cn.linjk.mybatistest.type;

public enum TypeStatus {
    normal(0),      // 正常
    history(1),     // 历史
    deleted(-1);    // 删除

    private final int value;

    private TypeStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
