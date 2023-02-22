package feudal.data;

import feudal.utils.wrappers.ItemStackWrapper;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Auction {
    @Getter
    private static final List<ItemStackWrapper> products = new ArrayList<>();
}
