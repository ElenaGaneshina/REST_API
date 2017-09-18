
package beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class GeoObject {

    @SerializedName("metaDataProperty")
    @Expose
    public MetaDataProperty_ metaDataProperty;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("boundedBy")
    @Expose
    public BoundedBy boundedBy;
    @SerializedName("Point")
    @Expose
    public Point point;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(metaDataProperty).append(description).append(name).append(boundedBy).append(point).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof GeoObject) == false) {
            return false;
        }
        GeoObject rhs = ((GeoObject) other);
        return new EqualsBuilder().append(metaDataProperty, rhs.metaDataProperty).append(description, rhs.description).append(name, rhs.name).append(boundedBy, rhs.boundedBy).append(point, rhs.point).isEquals();
    }

}
