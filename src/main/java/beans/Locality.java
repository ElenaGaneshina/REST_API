
package beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Locality {

    @SerializedName("LocalityName")
    @Expose
    public String localityName;
    @SerializedName("Thoroughfare")
    @Expose
    public Thoroughfare thoroughfare;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(localityName).append(thoroughfare).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Locality) == false) {
            return false;
        }
        Locality rhs = ((Locality) other);
        return new EqualsBuilder().append(localityName, rhs.localityName).append(thoroughfare, rhs.thoroughfare).isEquals();
    }

}
