package com.example.proj;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DB_sqlite extends SQLiteOpenHelper {
    public static final String DB_NAME = "Productsdb.db";

    public DB_sqlite(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE category (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "description TEXT)");

        db.execSQL("CREATE TABLE item (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "logo TEXT, " +
                "description TEXT, " +
                "price REAL NOT NULL, " +
                "categoryID INTEGER, " +
                "FOREIGN KEY (categoryID) REFERENCES category(ID))");

        db.execSQL("CREATE TABLE review (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "item_id INTEGER, " +
                "name TEXT, " +
                "body TEXT, " +
                "rating INTEGER, " +
                "FOREIGN KEY (item_id) REFERENCES item(ID))");

        insertCategory(db, "Motherboard", "Connecting key components for computer functionality.");
        insertCategory(db, "CPU", "Responsible for executing instructions.");
        insertCategory(db, "GPU", "Handles tasks related to visual rendering.");
        insertCategory(db, "RAM", "Volatile memory for rapid data access.");
        insertCategory(db, "Storage", "Data storage devices for computers.");
        insertCategory(db, "Power Supply", "Provides electrical power to components.");
        insertCategory(db, "ReadyPC", "Fully assembled and configured PCs.");

        insertItem(db, "MSI PRO B650-P WIFI Motherboard", "m1", "The PRO B650-P WIFI is a compact ATX motherboard outfitted with the AMD B650 chipset (AM5, Ryzen 7000 ready); The VRM features MSI Core Boost technology for improved stability & performance", 999.00, 1);
        insertItem(db, "ASRock B650M Motherboard", "m2", "Supports AMD Ryzen 7000 Series Processors and DDR 5 Ram", 770.00, 1);
        insertItem(db, "AMD Ryzen™ 7 7700X 8-Core", "c1", "5.4 GHz Max Boost, unlocked for overclocking, 80 MB cache, DDR5-5200 support", 1682.00, 2);
        insertItem(db, "AMD Ryzen 7 7800X3D", "c2", "8 MB L2 + 96 MB L3 cache memory provides excellent hit rate in short access time enabling improved system performance", 1900.00, 2);
        insertItem(db, "AMD Ryzen™ 5 7600", "c3", "5.2 GHz Max Boost, unlocked for overclocking, 38 MB cache, DDR5-5200 support", 1000.00, 2);
        insertItem(db, "PowerColor AMD Radeon RX 7900 XT Graphics Card", "g1", "Memory Clock: 20.0 Gbps, Memory Interface: 320-bit. 1 x HDMI 2.1, 2x DisplayPort 2.1, 1x Type-C", 4680.00, 3);
        insertItem(db, "MSI Gaming GeForce RTX 4080 16GB", "g2", "Ada Lovelace Architecture Graphics Card (Gaming X Trio)", 6000.00, 3);
        insertItem(db, "Zotac Gaming Geforce RTX 3060 Ti 8GB GDDR6", "g3", "NVIDIA Ampere architecture, 2nd Gen Ray Tracing Cores, 3rd Gen Tensor Cores", 2450.00, 3);
        insertItem(db, "Corsair DDR5 32GB (2x16GB) 5600MHz", "r1", "Push The Limits Of Your System Like Never Before With Corsair DDR5 Memory, Unlocking Even Faster Frequencies, Greater Capacities, And Higher CPU Processing", 339.00, 4);
        insertItem(db, "VENGEANCE RGB DDR5 RAM 32GB (2x16GB) 6000MHz", "r2", "Maximum Bandwidth and Tight Response Times: Optimized for peak performance on the latest Intel DDR5 motherboards", 500.00, 4);
        insertItem(db, "Crucial P3 Plus 1TB M.2 SSD", "s1", "NVMe (PCIe Gen4 x4) technology with up to 5000MB/s sequential reads, random read/write 650K/800K IOPS", 294.00, 5);
        insertItem(db, "Ediloca 1TB NVMe SSD 3D NAND1.3 PCIe Gen3x4 M.2", "s2", "EN605 NVMe SSD can reduce OS and game/software latency, providing stable and smooth gaming/office experience", 180.00, 5);
        insertItem(db, "Corsair RM750e 80 PLUS Gold Efficiency", "p1", "Reliable and efficient low-noise power supply with fully modular cabling, so you only connect the cables your system build needs", 670.00, 6);
        insertItem(db, "Cooler Master 80 PLUS Gold 650W Power Supply", "p2", "90% efficiency makes 80 PLUS Gold ideal for PC gaming systems with high energy demands - saving on utility costs and improving overall performance by reducing heat accumulation due to inefficient energy conversion", 480.00, 6);
        insertItem(db, "ROG GAMING DESKTOP", "rp1", "INTEL CORE I7 11700KF 3.6GHz /16GB DDR4 /1TB SATA + 256SSD / NVIDIA GeForce GTX 1660 Ti 6GB / WIN 10", 3699.00, 7);
        insertItem(db, "Dell Vostro 3910 Business Desktop", "rp2", "Intel Core i5-12400, 16GB DDR4 RAM, 256GB NVME SSD, 1TB HDD, 802.11AX WiFi, Bluetooth 5.2, HDMI, VGA, Windows 11 Pro", 2190.00, 7);
        insertItem(db, "ZORD AURORA ULTRA GAMING", "rp3", "13th GENERATION i7 PROCESSOR, RTX 4070 TI VIDEO CARD, 32GB MEMORY, WINDOWS 11 PRO", 8499.00, 7);


        insertReview(db,1,"Sarah Smith","The MSI PRO B650-P WIFI motherboard has been a reliable choice for my system. The AMD B650 chipset and Core Boost technology deliver noticeable stability and improved performance. At a price of 999.00 SAR, it offers good value for the features it provides.",4);
        insertReview(db,1,"Olivia Bennett","Impressed with the PRO B650-P WIFI. It handles Ryzen 7000 smoothly, and the Core Boost technology really shows its magic. Solid investment.",4);
        insertReview(db,1,"Marcus Rodriguez","The MSI B650-P WIFI has been the backbone of my system. It\"s compact, reliable, and the Core Boost tech gives it an extra edge. Definitely recommend.",3);
        insertReview(db,1,"Emily Chen","The AMD B650 chipset on this motherboard is a game-changer. Paired with Core Boost, it delivers on stability and performance. Worth every SAR.",4);
        insertReview(db,1,"David Nguyen","The MSI PRO B650-P WIFI is a solid choice. The AM5 compatibility is forward-thinking, and the Core Boost technology ensures a smooth computing experience.",4);
        insertReview(db,2,"Alex Thompson","The ASRock B650M motherboard is a budget-friendly option with AMD Ryzen 7000 Series support. Great value for the price!",4);
        insertReview(db,2,"Sophia Reynolds","I\"ve had a positive experience with the ASRock B650M. It smoothly handles AMD Ryzen 7000 Series processors, and the DDR5 RAM support is a plus.",4);
        insertReview(db,2,"Malik Patel","ASRock B650M offers solid performance at an affordable price. Ryzen 7000 compatibility and DDR5 support make it a smart choice for budget-conscious users.",3);
        insertReview(db,2,"Emily Watson","The ASRock B650M is a reliable motherboard for those on a budget. The AMD Ryzen 7000 Series support and DDR5 RAM compatibility add to its appeal.",3);
        insertReview(db,2,"Ryan Mitchell","For its price, the ASRock B650M is a good deal. It handles Ryzen 7000 processors well, and the DDR5 RAM support future-proofs your system.",4);
        insertReview(db,3,"Olivia Foster","The AMD Ryzen™ 7 7700X is a powerhouse with its 5.4 GHz Max Boost and 8 cores. Unlocked for overclocking adds flexibility, and the DDR5-5200 support is impressive.",4);
        insertReview(db,3,"Jason Reed","Impressed with the AMD Ryzen™ 7 7700X. The unlocked overclocking feature is a game-changer, and the DDR5-5200 support ensures high-speed performance.",4);
        insertReview(db,3,"Aisha Abdullah","The AMD Ryzen™ 7 7700X is a beast with its 5.4 GHz boost and DDR5-5200 support. Unlocked for overclocking is a nice touch for enthusiasts.",4);
        insertReview(db,3,"Ethan Hayes","The price-performance ratio of the AMD Ryzen™ 7 7700X is excellent. The 5.4 GHz boost and DDR5-5200 support make it a solid choice for high-end users.",4);
        insertReview(db,3,"Maya Patel","The AMD Ryzen™ 7 7700X delivers impressive speed with its 5.4 GHz boost, and the unlocked overclocking feature is perfect for fine-tuning performance.",4);
        insertReview(db,4,"Daniel Carter","The AMD Ryzen 7 7800X3D offers impressive cache memory with 8 MB L2 and a massive 96 MB L3. This translates to excellent system performance.",4);
        insertReview(db,4,"Sara Al-Mansoori","The AMD Ryzen 7 7800X3D\"s substantial 96 MB L3 cache is a game-changer for system responsiveness. Well worth the price for enhanced performance.",4);
        insertReview(db,4,"Javier Rodriguez","The AMD Ryzen 7 7800X3D is a solid choice for performance enthusiasts. The 96 MB L3 cache ensures smooth operation, making it worth the investment.",4);
        insertReview(db,4,"Lily Chang","Impressed with the cache setup on the AMD Ryzen 7 7800X3D. The 8 MB L2 and massive 96 MB L3 contribute to a noticeable boost in system speed.",4);
        insertReview(db,4,"Ahmed Abdullah","The AMD Ryzen 7 7800X3D\"s cache configuration is top-notch, providing excellent system performance. At 1900.00 SAR, it\"s a worthy investment.",4);
        insertReview(db,5,"Chloe Thompson","The AMD Ryzen™ 5 7600 is a decent processor with a 5.2 GHz Max Boost and DDR5-5200 support. The unlocked overclocking adds value for enthusiasts.",4);
        insertReview(db,5,"Muhammad Ali","The AMD Ryzen™ 5 7600 offers good performance at its price point. The 5.2 GHz boost and DDR5-5200 support make it a suitable choice for mid-range builds.",3);
        insertReview(db,5,"Isabella Chen","The AMD Ryzen™ 5 7600 provides reasonable specs for the price. The 5.2 GHz boost and DDR5-5200 support are commendable, but consider other options for higher performance.",3);
        insertReview(db,5,"Ahmed Khan","The AMD Ryzen™ 5 7600 is an affordable choice with decent specs. The 5.2 GHz boost and DDR5-5200 support offer good value for budget-conscious users.",4);
        insertReview(db,5,"Sophie Nguyen","While the AMD Ryzen™ 5 7600 is budget-friendly, the 5.2 GHz boost and DDR5-5200 support make it a competitive option for mid-range systems.",4);
        insertReview(db,6,"Oliver Davis","The PowerColor AMD Radeon RX 7900 XT is a beast with its 20.0 Gbps Memory Clock and extensive port options. A high-performing graphics card, but the price is a bit steep.",4);
        insertReview(db,6,"Layla Ahmed","The RX 7900 XT from PowerColor is a powerhouse. The 20.0 Gbps Memory Clock and versatile ports, including HDMI 2.1 and DisplayPort 2.1, make it a solid choice for gaming and content creation.",4);
        insertReview(db,6,"Javier Rodriguez","The PowerColor AMD Radeon RX 7900 XT offers impressive specs with a 20.0 Gbps Memory Clock and a variety of ports. However, the price may be a deterrent for budget-conscious users.",3);
        insertReview(db,6,"Aisha Malik","A high-end option with a 20.0 Gbps Memory Clock and excellent port selection. The PowerColor RX 7900 XT is a top-tier graphics card, but the price reflects that.",4);
        insertReview(db,6,"Ryan Mitchell","The PowerColor AMD Radeon RX 7900 XT is a premium graphics card with a 20.0 Gbps Memory Clock and versatile port options. While it\"s powerful, the price might be a consideration for some buyers.",3);
        insertReview(db,7,"Alex Turner","The MSI Gaming GeForce RTX 4080 with Ada Lovelace architecture is a gaming powerhouse. The Gaming X Trio design adds to its appeal. However, the price is on the higher side.",4);
        insertReview(db,7,"Sofia Rodriguez","MSI\"s RTX 4080 is a beast for gaming with Ada Lovelace architecture. The Gaming X Trio enhances its aesthetics, but the price might be a limiting factor for some.",4);
        insertReview(db,7,"Omar Al-Mansoori","The MSI Gaming GeForce RTX 4080 impresses with Ada Lovelace architecture and the Gaming X Trio design. However, the price may not be justified for all users.",3);
        insertReview(db,7,"Emily Foster","The RTX 4080 from MSI is a premium graphics card with Ada Lovelace architecture. The Gaming X Trio design is a nice touch, but the high price may limit its appeal.",3);
        insertReview(db,7,"Ahmed Khan","MSI\"s RTX 4080 is a solid performer with Ada Lovelace architecture. The Gaming X Trio design adds flair, but the price might be steep for some users.",4);
        insertReview(db,8,"Alex Turner","The Zotac RTX 3060 Ti is a fantastic deal! The NVIDIA Ampere architecture and 2nd Gen Ray Tracing Cores deliver superb gaming performance for the price.",4);
        insertReview(db,8,"Sophia Rodriguez","I\"m thrilled with the Zotac RTX 3060 Ti. The 8GB GDDR6 and 2nd Gen Ray Tracing Cores make it a solid choice for a mid-range GPU. Great value at 2450.00 SAR!",4);
        insertReview(db,8,"Emily Foster","The Zotac Gaming GeForce RTX 3060 Ti exceeded my expectations. The NVIDIA Ampere architecture and 2nd Gen Ray Tracing Cores deliver smooth gaming experiences.",4);
        insertReview(db,8,"Ahmed Khan","Value for money is excellent with the Zotac RTX 3060 Ti. The NVIDIA Ampere architecture ensures top-notch performance, making me a satisfied customer.",4);
        insertReview(db,8,"Jason Reed","Disappointed with the Zotac RTX 3060 Ti. Despite the NVIDIA Ampere architecture, I experienced issues that impacted my gaming experience. Expected better performance.",2);
        insertReview(db,9,"Olivia Bennett","Corsair DDR5 32GB kit is a game-changer! Unleashing my system\"s potential with faster frequencies and greater capacities. The price is reasonable for the performance boost.",4);
        insertReview(db,9,"Malik Patel","Corsair\"s DDR5 32GB kit is a solid investment. My system runs smoother with the higher frequencies, and the 5600MHz speed makes a noticeable difference in performance.",4);
        insertReview(db,9,"Lily Chang","Thrilled with Corsair DDR5 32GB! It pushes my system to new heights with faster frequencies. The 5600MHz speed is impressive, and the price is justified for the performance boost.",4);
        insertReview(db,9,"Javier Rodriguez","Corsair DDR5 32GB enhances my system\"s capabilities. The higher frequencies and greater capacities are noticeable, providing a smoother and more responsive experience.",3);
        insertReview(db,9,"Emily Watson","Disappointed with Corsair DDR5 32GB. Despite the promise of higher frequencies, I didn\"t experience a significant performance boost. The price seems a bit high for the marginal improvement.",2);
        insertReview(db,10,"Emma Turner","VENGEANCE RGB DDR5 RAM is a beast! The 6000MHz speed is phenomenal, providing maximum bandwidth and tight response times. The RGB adds a stylish touch. Well worth the price!",4);
        insertReview(db,10,"Ahmed Abdullah","My system is on fire with VENGEANCE RGB DDR5 RAM. The 6000MHz speed delivers incredible performance, and the RGB lighting adds a touch of flair. Great value for 500.00 SAR.",4);
        insertReview(db,10,"Sophia Rodriguez","VENGEANCE RGB DDR5 RAM is a game-changer! The 6000MHz speed ensures peak performance, and the RGB lighting adds a cool factor. Perfect for the latest Intel DDR5 motherboards.",4);
        insertReview(db,10,"Daniel Carter"," Impressed with VENGEANCE RGB DDR5 RAM. The 6000MHz speed optimizes my system\"s performance, and the RGB lighting is a nice touch. The price is justified for the quality.",3);
        insertReview(db,10,"Olivia Foster","VENGEANCE RGB DDR5 RAM didn\"t meet my expectations. The 6000MHz speed didn\"t provide a significant performance boost, and the RGB seems more for show than substance. Pricey for the results.",2);
        insertReview(db,11,"Aisha Malik","The Crucial P3 Plus 1TB M.2 SSD is a speed demon! With NVMe Gen4 technology, the 5000MB/s sequential reads are impressive. Excellent value at 294.00 SAR.",4);
        insertReview(db,11,"Javier Rodriguez","Loving the Crucial P3 Plus 1TB M.2 SSD! The NVMe Gen4 tech ensures lightning-fast performance. With 5000MB/s sequential reads, my system runs like a dream. Great deal for the price!",4);
        insertReview(db,11,"Lily Chang","Impressed with the Crucial P3 Plus 1TB M.2 SSD! The NVMe Gen4 and 5000MB/s sequential reads provide unmatched speed. At 294.00 SAR, it\"s a steal for the performance.",4);
        insertReview(db,11,"Emily Watson","Disappointed with the Crucial P3 Plus 1TB M.2 SSD. Despite the advertised speeds, I didn\"t experience a significant performance boost. The price seems high for the marginal improvement.",2);
        insertReview(db,12,"Sarah Smith","The Ediloca 1TB NVMe SSD is a hidden gem! The EN605 NVMe technology reduces latency, providing a stable and smooth gaming experience. Unbeatable value at 180.00 SAR.",4);
        insertReview(db,12,"Ahmed Abdullah","The Ediloca 1TB NVMe SSD is a pleasant surprise! EN605 technology delivers on its promise, reducing latency for a smooth gaming and office experience. Great deal for the price.",4);
        insertReview(db,12,"Emma Turner","Loving the Ediloca 1TB NVMe SSD! The EN605 technology works wonders, reducing OS and game/software latency. My system now runs like a dream. Outstanding value at 180.00 SAR.",4);
        insertReview(db,12,"Javier Rodriguez","Impressed with the Ediloca 1TB NVMe SSD! The EN605 technology delivers a stable and smooth gaming/office experience. At 180.00 SAR, it\"s a steal for the performance.",3);
        insertReview(db,12,"Olivia Foster","Disappointed with the Ediloca 1TB NVMe SSD. Despite the claims of reducing latency, I didn\"t notice a significant improvement in my gaming experience. The price is attractive, but the performance falls short.",1);
        insertReview(db,13,"Alex Turner","The Corsair RM750e is a reliable workhorse! The 80 PLUS Gold efficiency ensures power is used efficiently. Fully modular cabling makes cable management a breeze. Well worth the 670.00 SAR.",4);
        insertReview(db,13,"Sophia Rodriguez","Loving the Corsair RM750e! It\"s a quiet and efficient power supply. The fully modular cabling is a game-changer for clean cable management. Excellent value at 670.00 SAR.",4);
        insertReview(db,13,"Malik Patel","The Corsair RM750e is a fantastic choice! The 80 PLUS Gold efficiency and fully modular cabling contribute to a reliable and clean power supply. Great deal for the price.",3);
        insertReview(db,13,"Lily Chang","Impressed with the Corsair RM750e! It\"s a low-noise power supply that lives up to its reputation. Fully modular cabling makes setup easy, and at 670.00 SAR, it\"s a steal.",3);
        insertReview(db,13,"Jason Reed","Disappointed with the Corsair RM750e. Despite being fully modular, I encountered issues with power fluctuations. The 80 PLUS Gold efficiency didn\"t seem to translate to stable power delivery. Pricey for the performance.",1);
        insertReview(db,14,"Olivia Bennett","The Cooler Master 80 PLUS Gold 650W is a great pick! With 90% efficiency, it\"s ideal for gaming systems with high energy demands. The cost savings on utilities and reduced heat accumulation are fantastic. Excellent value at 480.00 SAR.",4);
        insertReview(db,14,"Ahmed Abdullah","Loving the Cooler Master 80 PLUS Gold! The 90% efficiency is a game-changer for my gaming rig, saving on utility costs. At 480.00 SAR, it\"s a steal for the performance.",4);
        insertReview(db,14,"Kaido","Impressed with the Cooler Master 80 PLUS Gold 650W! The 90% efficiency is noticeable, and it\"s perfect for my gaming system. The price of 480.00 SAR makes it a budget-friendly choice.",3);
        insertReview(db,14,"Shanks","The Cooler Master 80 PLUS Gold is a reliable choice. The 90% efficiency ensures optimal performance for gaming systems. The price of 480.00 SAR adds to its appeal.",3);
        insertReview(db,14,"Sophia Rodriguez","Disappointed with the Cooler Master 80 PLUS Gold. Despite the claims of 90% efficiency, I didn\"t notice a significant improvement. The power supply seemed to struggle under heavy loads. Expected better performance for the price.",2);
        insertReview(db,15,"Oden","The ROG Gaming Desktop is a powerhouse! With an Intel Core i7 11700KF, 16GB DDR4, and a NVIDIA GeForce GTX 1660 Ti, it handles gaming and multitasking with ease. Great value at 3699.00 SAR.",5);
        insertReview(db,15,"Malenia","Impressed with the ROG Gaming Desktop! The Intel Core i7 11700KF and GTX 1660 Ti deliver solid gaming performance. The 1TB SATA + 256GB SSD combo provides ample storage. Well worth the 3699.00 SAR.",4);
        insertReview(db,15,"Mohannad","The ROG Gaming Desktop is a beast! The Intel Core i7 11700KF and NVIDIA GeForce GTX 1660 Ti make it a gaming powerhouse. The 1TB SATA + 256GB SSD combo ensures speedy storage. Excellent value at 3699.00 SAR.",4);
        insertReview(db,15,"Mosab","Loving my ROG Gaming Desktop! The Intel Core i7 11700KF and GTX 1660 Ti combo provides a smooth gaming experience. The 1TB SATA + 256GB SSD cover my storage needs perfectly. Great deal at 3699.00 SAR.",3);
        insertReview(db,15,"Jason Reed","Disappointed with the ROG Gaming Desktop. The Intel Core i7 and GTX 1660 Ti are great, but the 1TB SATA + 256GB SSD combo feels limiting. Expected more storage options for the price.",2);
        insertReview(db,16,"Mohg","The Dell Vostro 3910 is a solid business desktop! The Intel Core i5-12400, 16GB DDR4 RAM, and the combo of 256GB NVME SSD + 1TB HDD make it a reliable workhorse. Windows 11 Pro is a bonus. Great value at 2190.00 SAR.",4);
        insertReview(db,16,"Malekith","Impressed with the Dell Vostro 3910! The Intel Core i5-12400 and the 16GB DDR4 RAM provide snappy performance. The 256GB NVME SSD + 1TB HDD combo offers ample storage. Windows 11 Pro is a nice addition. Excellent deal at 2190.00 SAR.",4);
        insertReview(db,16,"Amir","The Dell Vostro 3910 is a reliable business companion! With an Intel Core i5-12400, 16GB DDR4 RAM, and the efficient storage combo, it meets all my work needs. The addition of Windows 11 Pro is a great feature. Well worth the 2190.00 SAR.",3);
        insertReview(db,16,"Shakir","Loving my Dell Vostro 3910! The Intel Core i5-12400 and 16GB DDR4 RAM make multitasking a breeze. The 256GB NVME SSD + 1TB HDD combo is perfect for my storage needs. Windows 11 Pro is a nice touch. Great value at 2190.00 SAR.",2);
        insertReview(db,16,"Zoro","Disappointed with the Dell Vostro 3910. While the specs are decent, the 256GB NVME SSD feels limiting for business use. Expected more storage options for the price.",2);
        insertReview(db,17,"Rayan","The ZORD AURORA ULTRA GAMING is a beast! The 13th Gen i7 processor, RTX 4070 Ti, 32GB memory, and Windows 11 Pro make it a gaming powerhouse. Excellent value at 8499.00 SAR.",4);
        insertReview(db,17,"White beard","Impressed with the ZORD AURORA ULTRA GAMING! The 13th Gen i7 processor and RTX 4070 Ti deliver top-tier gaming performance. The 32GB memory ensures smooth multitasking. Windows 11 Pro is a great addition. Well worth the 8499.00 SAR.",4);
        insertReview(db,17,"Luffy","The ZORD AURORA ULTRA GAMING is a gaming dream come true! With the 13th Gen i7 processor, RTX 4070 Ti, and 32GB memory, it handles anything I throw at it. Windows 11 Pro is a nice bonus. Great deal at 8499.00 SAR.",5);
        insertReview(db,17,"Mohammed abdu","Loving my ZORD AURORA ULTRA GAMING! The 13th Gen i7 processor and RTX 4070 Ti provide unmatched gaming performance. The 32GB memory ensures smooth multitasking. Windows 11 Pro completes the package. Excellent value at 8499.00 SAR.",3);
        insertReview(db,17,"Mansoor","Disappointed with the ZORD AURORA ULTRA GAMING. While the specs are impressive, the price of 8499.00 SAR seems steep. Expected more value for the money.",1);

    }

    // Helper method to insert a category
    private void insertCategory(SQLiteDatabase db, String name, String description) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("description", description);
        db.insert("category", null, values);
    }

    // Helper method to insert an item
    private void insertItem(SQLiteDatabase db, String name, String logo, String description, double price, int categoryID) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("logo", logo);  // Assuming logo is a reference to the drawable resource
        values.put("description", description);
        values.put("price", price);
        values.put("categoryID", categoryID);
        db.insert("item", null, values);
    }

    // Helper method to insert a review
    private void insertReview(SQLiteDatabase db, int item_id, String name, String body, int rating) {
        ContentValues values = new ContentValues();
        values.put("item_id", item_id);
        values.put("name", name);
        values.put("body", body);
        values.put("rating", rating);
        db.insert("review", null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop existing tables and recreate them when upgrading
        db.execSQL("DROP TABLE IF EXISTS item");
        db.execSQL("DROP TABLE IF EXISTS category");
        db.execSQL("DROP TABLE IF EXISTS review");
        onCreate(db);
    }

    // Method to insert a new item (used elsewhere in your app)
    public Boolean insertNewItem(String name, String logo, String description, double price, int categoryID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("logo", logo);
        contentValues.put("description", description);
        contentValues.put("price", price);
        contentValues.put("categoryID", categoryID);
        long result = db.insert("item", null, contentValues);

        return result != -1;
    }
}
