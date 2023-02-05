package feudal.auction;

import feudal.utils.wrappers.ItemStackWrapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class AuctionTest {
    /**
     * Method under test: {@link Auction#addProduct(ItemStackWrapper)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddProduct() {
        // TODO: Complete this test.
        //   Reason: R006 Static initializer failed.
        //   The static initializer of
        //   feudal.auction.Auction
        //   threw java.lang.NullPointerException while trying to load it.
        //   Make sure the static initializer of Auction
        //   can be executed without throwing exceptions.
        //   Exception: java.lang.NullPointerException
        //       at feudal.auction.Auction.<clinit>(Auction.java:18)
        //       at java.lang.Class.forName0(Native Method)
        //       at java.lang.Class.forName(Class.java:348)

        // Arrange
        // TODO: Populate arranged inputs
        ItemStackWrapper product = null;

        // Act
        Auction.addProduct(product);

        // Assert
        // TODO: Add assertions on result
    }
}

