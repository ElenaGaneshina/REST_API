
package beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class AdministrativeArea {

    @SerializedName("AdministrativeAreaName")
    @Expose
    public String administrativeAreaName;
    @SerializedName("Locality")
    @Expose
    public Locality locality;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(administrativeAreaName).append(locality).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof AdministrativeArea) == false) {
            return false;
        }
        AdministrativeArea rhs = ((AdministrativeArea) other);
        return new EqualsBuilder().append(administrativeAreaName, rhs.administrativeAreaName).append(locality, rhs.locality).isEquals();
    }

}
