/*
 * Reference Code Source
 *
 * @Source: https://github.com/apache/commons-lang
 * @GroupId: org.apache.commons
 * @ArtifactId: commons-lang3
 * @Version: 3.10
 * @Package: org.apache.commons.lang3.builder
 * @Class: ToStringStyle.JsonToStringStyle.java
 */
package com.cafe.common.util.builder;

import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.util.builder
 * @Author: zhouboyi
 * @Date: 2025/4/15 17:30
 * @Description: ToString 样式 (JSON 风格, 非 ASCII 码字符不转 Unicode 编码)
 */
public class JsonToStringStyleWithoutUnicode extends ToStringStyle {

    private static final long serialVersionUID = 1L;

    private static final String FIELD_NAME_QUOTE = "\"";

    JsonToStringStyleWithoutUnicode() {
        this.setUseClassName(false);
        this.setUseIdentityHashCode(false);
        this.setContentStart("{");
        this.setContentEnd("}");
        this.setArrayStart("[");
        this.setArrayEnd("]");
        this.setFieldSeparator(",");
        this.setFieldNameValueSeparator(":");
        this.setNullText("null");
        this.setSummaryObjectStartText("\"<");
        this.setSummaryObjectEndText(">\"");
        this.setSizeStartText("\"<size=");
        this.setSizeEndText(">\"");
    }

    @Override
    public void append(StringBuffer buffer, String fieldName, Object[] array, Boolean fullDetail) {
        if (fieldName == null) {
            throw new UnsupportedOperationException("Field names are mandatory when using JsonToStringStyle");
        } else if (!this.isFullDetail(fullDetail)) {
            throw new UnsupportedOperationException("FullDetail must be true when using JsonToStringStyle");
        } else {
            super.append(buffer, fieldName, array, fullDetail);
        }
    }

    @Override
    public void append(StringBuffer buffer, String fieldName, long[] array, Boolean fullDetail) {
        if (fieldName == null) {
            throw new UnsupportedOperationException("Field names are mandatory when using JsonToStringStyle");
        } else if (!this.isFullDetail(fullDetail)) {
            throw new UnsupportedOperationException("FullDetail must be true when using JsonToStringStyle");
        } else {
            super.append(buffer, fieldName, array, fullDetail);
        }
    }

    @Override
    public void append(StringBuffer buffer, String fieldName, int[] array, Boolean fullDetail) {
        if (fieldName == null) {
            throw new UnsupportedOperationException("Field names are mandatory when using JsonToStringStyle");
        } else if (!this.isFullDetail(fullDetail)) {
            throw new UnsupportedOperationException("FullDetail must be true when using JsonToStringStyle");
        } else {
            super.append(buffer, fieldName, array, fullDetail);
        }
    }

    @Override
    public void append(StringBuffer buffer, String fieldName, short[] array, Boolean fullDetail) {
        if (fieldName == null) {
            throw new UnsupportedOperationException("Field names are mandatory when using JsonToStringStyle");
        } else if (!this.isFullDetail(fullDetail)) {
            throw new UnsupportedOperationException("FullDetail must be true when using JsonToStringStyle");
        } else {
            super.append(buffer, fieldName, array, fullDetail);
        }
    }

    @Override
    public void append(StringBuffer buffer, String fieldName, byte[] array, Boolean fullDetail) {
        if (fieldName == null) {
            throw new UnsupportedOperationException("Field names are mandatory when using JsonToStringStyle");
        } else if (!this.isFullDetail(fullDetail)) {
            throw new UnsupportedOperationException("FullDetail must be true when using JsonToStringStyle");
        } else {
            super.append(buffer, fieldName, array, fullDetail);
        }
    }

    @Override
    public void append(StringBuffer buffer, String fieldName, char[] array, Boolean fullDetail) {
        if (fieldName == null) {
            throw new UnsupportedOperationException("Field names are mandatory when using JsonToStringStyle");
        } else if (!this.isFullDetail(fullDetail)) {
            throw new UnsupportedOperationException("FullDetail must be true when using JsonToStringStyle");
        } else {
            super.append(buffer, fieldName, array, fullDetail);
        }
    }

    @Override
    public void append(StringBuffer buffer, String fieldName, double[] array, Boolean fullDetail) {
        if (fieldName == null) {
            throw new UnsupportedOperationException("Field names are mandatory when using JsonToStringStyle");
        } else if (!this.isFullDetail(fullDetail)) {
            throw new UnsupportedOperationException("FullDetail must be true when using JsonToStringStyle");
        } else {
            super.append(buffer, fieldName, array, fullDetail);
        }
    }

    @Override
    public void append(StringBuffer buffer, String fieldName, float[] array, Boolean fullDetail) {
        if (fieldName == null) {
            throw new UnsupportedOperationException("Field names are mandatory when using JsonToStringStyle");
        } else if (!this.isFullDetail(fullDetail)) {
            throw new UnsupportedOperationException("FullDetail must be true when using JsonToStringStyle");
        } else {
            super.append(buffer, fieldName, array, fullDetail);
        }
    }

    @Override
    public void append(StringBuffer buffer, String fieldName, boolean[] array, Boolean fullDetail) {
        if (fieldName == null) {
            throw new UnsupportedOperationException("Field names are mandatory when using JsonToStringStyle");
        } else if (!this.isFullDetail(fullDetail)) {
            throw new UnsupportedOperationException("FullDetail must be true when using JsonToStringStyle");
        } else {
            super.append(buffer, fieldName, array, fullDetail);
        }
    }

    @Override
    public void append(StringBuffer buffer, String fieldName, Object value, Boolean fullDetail) {
        if (fieldName == null) {
            throw new UnsupportedOperationException("Field names are mandatory when using JsonToStringStyle");
        } else if (!this.isFullDetail(fullDetail)) {
            throw new UnsupportedOperationException("FullDetail must be true when using JsonToStringStyle");
        } else {
            super.append(buffer, fieldName, value, fullDetail);
        }
    }

    @Override
    protected void appendDetail(StringBuffer buffer, String fieldName, char value) {
        this.appendValueAsString(buffer, String.valueOf(value));
    }

    @Override
    protected void appendDetail(StringBuffer buffer, String fieldName, Object value) {
        if (value == null) {
            this.appendNullText(buffer, fieldName);
        } else if (!(value instanceof String) && !(value instanceof Character)) {
            if (!(value instanceof Number) && !(value instanceof Boolean)) {
                String valueAsString = value.toString();
                if (!this.isJsonObject(valueAsString) && !this.isJsonArray(valueAsString)) {
                    this.appendDetail(buffer, fieldName, valueAsString);
                } else {
                    buffer.append(value);
                }
            } else {
                buffer.append(value);
            }
        } else {
            this.appendValueAsString(buffer, value.toString());
        }
    }

    private boolean isJsonArray(String valueAsString) {
        return valueAsString.startsWith(this.getArrayStart()) && valueAsString.endsWith(this.getArrayEnd());
    }

    private boolean isJsonObject(String valueAsString) {
        return valueAsString.startsWith(this.getContentStart()) && valueAsString.endsWith(this.getContentEnd());
    }

    private void appendValueAsString(StringBuffer buffer, String value) {
        buffer.append('"').append(value).append('"');
    }

    @Override
    protected void appendFieldStart(StringBuffer buffer, String fieldName) {
        if (fieldName == null) {
            throw new UnsupportedOperationException("Field names are mandatory when using JsonToStringStyle");
        } else {
            super.appendFieldStart(buffer, "\"" + fieldName + "\"");
        }
    }

    private Object readResolve() {
        return ToStringStyleHolder.JSON_STYLE_WITHOUT_UNICODE;
    }
}
