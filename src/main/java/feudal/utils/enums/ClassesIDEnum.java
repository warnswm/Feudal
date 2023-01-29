package feudal.utils.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ClassesIDEnum {
    BUILDER(1), COOK(2),FARMER(3),
    FISHERMAN(4), HUNTER(5), MINER(6),
    SHEPHERD(7), TRADER(8), WOODCUTTER(9),
    ALCHEMIST(10), BLACKSMITH(11), CLERK(12),
    GUARD(13), HEALER(14), KILLER(15),
    BARON(16), KING(17), KNIGHT(18),
    SQUIRE(19);
    int id;
}
