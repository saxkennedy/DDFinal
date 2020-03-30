package edu.bsu.cs222.dndcharactergenerator;

import java.util.Map;

public enum Background {
    ACOLYTE("Insight","Religion",
            "You have spent your life in the service of a temple\n" +
                    "to a specific god or pantheon of gods. You act as an\n" +
                    "intermediary between the realm of the holy and the\n" +
                    "mortal world, performing sacred rites and offering\n" +
                    "sacrifices in order to conduct worshipers into the\n" +
                    "presence o f the divine. You are not necessarily a\n" +
                    "cleric-performing sacred rites is not the same thing as\n" +
                    "channeling divine power.\n" +
                    "Choose a god, a pantheon of gods, or some other\n" +
                    "quasi-divine being from among those listed in appendix\n" +
                    "B or those specified by your DM, and work with your\n" +
                    "DM to detail the nature of your religious service.\n" +
                    "Were you a lesser functionary in a temple, raised from\n" +
                    "childhood to assist the priests in the sacred rites? Or\n" +
                    "were you a high priest who suddenly experienced a call\n" +
                    "to serve your god in a different way? Perhaps you were\n" +
                    "the leader of a small cult outside of any established\n" +
                    "temple structure, or even an occult",
            "SHELTER OF THE FAITHFUL: As an acolyte, you command the respect of those who\n" +
            "share your faith, and you can perform the religious\n" +
            "ceremonies of your deity. You and your adventuring\n" +
            "companions can expect to receive free healing and\n" +
            "care at a temple, shrine, or other established presence\n" +
            "of your faith, though you must provide any material\n" +
            "components needed for spells. Those who share\n" +
            "your religion will support you (but only you) at a\n" +
            "modest lifestyle.\n" +
            "You might also have ties to a specific temple dedicated\n" +
            "to your chosen deity or pantheon, and you have a\n" +
            "residence there. This could be the temple where you\n" +
            "used to serve, if you remain on good terms with it, or a\n" +
            "temple where you have found a new home. While near\n" +
            "your temple, you can call upon the priests for assistance,\n" +
            "provided the assistance you ask for is not"),
    CHARLATAN("Deception","Sleight of Hand","You have always had a way with people. You know\n" +
            "what makes them tick, you can tease out their hearts'\n" +
            "desires after a few minutes of conversation, and with a\n" +
            "few leading questions you can read them like they were\n" +
            "children's books. It’s a useful talent, and one that you’re\n" +
            "perfectly willing to use for your advantage.\n" +
            "You know what people want and you deliver, or rather,\n" +
            "you promise to deliver. Common sense should steer\n" +
            "people away from things that sound too good to be true,\n" +
            "but common sense seems to be in short supply when\n" +
            "you’re around. The bottle of pink-colored liquid will\n" +
            "surely cure that unseemly rash, this ointment—nothing\n" +
            "more than a bit of fat with a sprinkle of silver dust—can\n" +
            "restore youth and vigor, and there’s a bridge in the city\n" +
            "that just happens to be for sale. These marvels sound\n" +
            "implausible, but you make them sound like the real deal.","FALSE IDENTITY: You have created a second identity that includes\n" +
            "documentation, established acquaintances, and\n" +
            "disguises that allow you to assume that persona.\n" +
            "Additionally, you can forge documents including official\n" +
            "papers and personal letters, as long as you have seen an\n" +
            "example o f the kind o f document or the handwriting you\n" +
            "are trying to copy.");

    public final String proficiency1;
    public final String proficiency2;
    public final String description;
    public final String feature;
    Background(String proficiency1, String proficency2,String description, String feature){
        this.proficiency1 =proficiency1;
        this.proficiency2=proficency2;
        this.description =description;
        this.feature = feature;
    }
}
