
package beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class GeocoderMetaData {

    @SerializedName("kind")
    @Expose
    public String kind;
    @SerializedName("text")
    @Expose
    public String text;
    @SerializedName("precision")
    @Expose
    public String precision;
    @SerializedName("Address")
    @Expose
    public Address address;
    @SerializedName("AddressDetails")
    @Expose
    public AddressDetails addressDetails;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(kind).append(text).append(precision).append(address).append(addressDetails).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof GeocoderMetaData) == false) {
            return false;
        }
        GeocoderMetaData rhs = ((GeocoderMetaData) other);
        return new EqualsBuilder().append(kind, rhs.kind).append(text, rhs.text).append(precision, rhs.precision).append(address, rhs.address).append(addressDetails, rhs.addressDetails).isEquals();
    }

}
