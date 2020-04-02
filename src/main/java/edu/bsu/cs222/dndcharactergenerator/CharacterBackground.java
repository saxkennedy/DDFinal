package edu.bsu.cs222.dndcharactergenerator;

import java.util.Map;

public enum CharacterBackground implements CharacterDescriptor{
    ACOLYTE("Acolyte",Skills.INSIGHT,Skills.RELIGION,
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
    CHARLATAN("Charlatan",Skills.DECEPTION,Skills.SLEIGHTOFHAND,"You have always had a way with people. You know\n" +
            "what makes them tick, you can tease out the desires of their\n" +
            "hearts after a few minutes of conversation, and with a\n" +
            "few leading questions you can read them like they were\n" +
            "books for children. It is a useful talent, and one that you’re\n" +
            "perfectly willing to use for your advantage.\n" +
            "You know what people want and you deliver, or rather,\n" +
            "you promise to deliver. Common sense should steer\n" +
            "people away from things that sound too good to be true,\n" +
            "but common sense seems to be in short supply when\n" +
            "you are around. The bottle of pink-colored liquid will\n" +
            "surely cure that unseemly rash, this ointment, nothing\n" +
            "more than a bit of fat with a sprinkle of silver dust, can\n" +
            "restore youth and vigor, and there is a bridge in the city\n" +
            "that just happens to be for sale. These marvels sound\n" +
            "implausible, but you make them sound like the real deal.","FALSE IDENTITY: You have created a second identity that includes\n" +
            "documentation, established acquaintances, and\n" +
            "disguises that allow you to assume that persona.\n" +
            "Additionally, you can forge documents including official\n" +
            "papers and personal letters, as long as you have seen an\n" +
            "example of the kind of document or the handwriting you\n" +
            "are trying to copy."),
    CRIMINAL("Criminal",Skills.DECEPTION,Skills.STEALTH,"You are an experienced criminal with a history of\n" +
            "breaking the law. You have spent a lot of time among\n" +
            "other criminals and still have contacts within the\n" +
            "criminal underworld. You are far closer than m ost people\n" +
            "to the world of murder, theft, and violence that pervades\n" +
            "the underbelly of civilization, and you have survived up to\n" +
            "this point by flouting the rules and regulations of society.","CRIMINAL CONTACT: You have a reliable and trustworthy contact who acts as\n" +
            "your liaison to a network of other criminals. You know\n" +
            "how to get messages to and from your contact, even\n" +
            "over great distances; specifically, you know the local\n" +
            "messengers, corrupt caravan masters, and seedy sailors\n" +
            "who can deliver messages for you."),
    ENTERTAINER("Entertainer",Skills.ACROBATICS,Skills.PERFORMANCE,"You thrive in front of an audience. You know how to\n" +
            "entrance them, entertain them, and even inspire them.\n" +
            "Your poetics can stir the hearts o f those who hear you,\n" +
            "awakening grief or joy, laughter or anger. Your music\n" +
            "raises their spirits or captures their sorrow. Your dance\n" +
            "steps captivate, your humor cuts to the quick. Whatever\n" +
            "techniques you use, your art is your life.","BY POPULAR DEMAND: You can always find a place to perform, usually in an\n" +
            "inn or tavern but possibly with a circus, at a theater, or\n" +
            "even in a the court of a noble. At such a place, you receive free\n" +
            "lodging and food of a modest or comfortable standard\n" +
            "(depending on the quality of the establishment), as\n" +
            "long as you perform each night. In addition, your\n" +
            "performance makes you something of a local figure.\n" +
            "When strangers recognize you in a town where you have\n" +
            "performed, they typically take a liking to you."),
    FOLKHERO("Folk Hero",Skills.ANIMALHANDLING,Skills.SURVIVAL,"You come from a humble social rank, but you are\n" +
            "destined for so much more. Already the people of\n" +
            "your home village regard you as their champion, and\n" +
            "your destiny calls you to stand against the tyrants and\n" +
            "monsters that threaten the common folk everywhere.","RUSTIC HOSPITALITY: Since you come from the ranks of the common folk,\n" +
            "you fit in among them with ease. You can find a place\n" +
            "to hide, rest, or recuperate among other commoners,\n" +
            "unless you have shown yourself to be a danger to\n" +
            "them. They will shield you from the law or anyone\n" +
            "else searching for you, though they w ill not risk\n" +
            "their lives for you."),
    GUILDARTISAN("Guild Artisan",Skills.INSIGHT,Skills.PERSUASION,"You are a member of an artisan guild, skilled in\n" +
            "a particular field and closely associated with other\n" +
            "artisans. You are an established part of the\n" +
            "mercantile world, freed by talent and wealth from the\n" +
            "constraints of a feudal social order. You learned your\n" +
            "skills as an apprentice to a master artisan, under the\n" +
            "sponsorship of your guild, until you became a master in\n" +
            "your own right.","GUILD MEMBERSHIP: As an established and respected member of a guild, you\n" +
            "can rely on certain benefits that membership provides.\n" +
            "Your fellow guild members will provide you with\n" +
            "lodging and food if necessary, and pay for your funeral\n" +
            "if needed. In some cities and towns, a guildhall offers a\n" +
            "central place to meet other members of your profession,\n" +
            "which can be a good place to meet potential patrons,\n" +
            "allies, or hirelings.\n" +
            "Guilds often wield tremendous political power. If\n" +
            "you are accused of a crime, your guild will support you\n" +
            "if a good case can be made for your innocence or the\n" +
            "crime is justifiable. You can also gain access to powerful\n" +
            "political figures through the guild, if you are a member\n" +
            "in good standing. Such connections might require the\n" +
            "donation of money or magic items to the guild’s coffers.\n" +
            "You must pay dues of 5 gp per month to the guild. If\n" +
            "you miss payments, you must make up back dues to\n" +
            "remain in the good graces of the guild."),
    HERMIT("Hermit",Skills.MEDICINE,Skills.RELIGION,"DISCOVERY: You lived in seclusion, either in a sheltered community\n" +
            "such as a monastery, or entirely a lone, for a formative\n" +
            "part of your life. In your time apart from the clamor of\n" +
            "society, you found quiet, solitude, and perhaps some of\n" +
            "the answers you were looking for.","The quiet seclusion o f your extended hermitage gave you\n" +
            "access to a unique and powerful discovery. The exact\n" +
            "nature of this revelation depends on the nature of your\n" +
            "seclusion. It might be a great truth about the cosmos,\n" +
            "the deities, the powerful beings of the outer planes, or\n" +
            "the forces of nature. It could be a site that no one else\n" +
            "has ever seen. You might have uncovered a fact that has\n" +
            "long been forgotten, or unearthed some relic of the past\n" +
            "that could rewrite history. It might be information that\n" +
            "would be damaging to the people w ho or consigned you\n" +
            "to exile, and hence the reason for your return to society.\n" +
            "Work with your DM to determine the details of your\n" +
            "discovery and its impact on the campaign."),
    NOBLE("Noble",Skills.HISTORY,Skills.PERSUASION,"You understand wealth, power, and privilege. You\n" +
            "carry a noble title, and your family owns land, collects\n" +
            "taxes, and wields significant political influence. You\n" +
            "might be a pampered aristocrat unfamiliar with\n" +
            "work or discomfort, a former merchant just elevated\n" +
            "to the nobility, or a disinherited scoundrel with a\n" +
            "disproportionate sense of entitlement. Or you could be\n" +
            "an honest, hardworking landowner who cares deeply\n" +
            "about the people who live and work on your land, keenly\n" +
            "aware of your responsibility to them.\n" +
            "Work with your DM to come up with an appropriate\n" +
            "title and determine how much authority that title\n" +
            "carries. A noble title does not stand on its own, it is\n" +
            "connected to an entire family, and whatever title you\n" +
            "hold, you will pass it down to your own children. Not\n" +
            "only do you need to determine your noble title, but you\n" +
            "should also work with the DM to describe your family\n" +
            "and their influence on you.\n" +
            "Is your family old and established, or was your title\n" +
            "only recently bestowed? How much influence do they\n" +
            "wield, and over what area? What kind of reputation\n" +
            "does your family have among the other aristocrats o f the\n" +
            "region? How do the common people regard them?\n" +
            "What is your position in the family? Are you the heir\n" +
            "to the head of the family? Have you already inherited\n" +
            "the title? How do you feel about that responsibility? Or\n" +
            "are you so far down the line of inheritance that no one\n" +
            "cares what you do, as long as you don not embarrass the\n" +
            "family? How does the head of your family feel about\n" +
            "your adventuring career? Are you in your the good\n" +
            "graces of your family, or shunned by them?\n" +
            "Does your family have a coat of arms? An insignia you\n" +
            "might wear on a signet ring? Particular colors you wear\n" +
            "all the time? An animal you regard as a symbol of your\n" +
            "line or even a spiritual member of the family?\n" +
            "These details help establish your family and your title\n" +
            "as features of the world of the campaign.","Thanks to your noble birth, people are inclined to\n" +
            "think the best of you. You are welcome in high society,\n" +
            "and people assume you have the right to be wherever\n" +
            "you are. The common folk make every effort to\n" +
            "accommodate you and avoid your displeasure, and other\n" +
            "people of high birth treat you as a member of the same\n" +
            "social sphere. You can secure an audience with a local\n" +
            "noble if you need to."),
    OUTLANDER("Outlander",Skills.ATHLETICS,Skills.SURVIVAL,"You grew up in the wilds, far from civilization and the\n" +
            "comforts of town and technology. You have witnessed the\n" +
            "migration of herds larger than forests, survived weather\n" +
            "more extreme than any city dweller could comprehend,\n" +
            "and enjoyed the solitude of being the only thinking\n" +
            "creature for miles in any direction. The wilds are in\n" +
            "your blood, whether you were a nomad, an explorer, a\n" +
            "recluse, a hunter gatherer, or even a marauder. Even in\n" +
            "places where you do not know the specific features of the\n" +
            "terrain, you know the ways of the wild.","WANDERER: You have an excellent memory for maps and geography,\n" +
            "and you can always recall the general layout of terrain,\n" +
            "settlements, and other features around you. In addition,\n" +
            "you can find food and fresh water for yourself and up to\n" +
            "five other people each day, provided that the land offers\n" +
            "berries, small game, water, and so forth."),
    SAGE("Sage",Skills.ARCANA,Skills.HISTORY,"You spent years learning the lore of the multiverse. You\n" +
            "scoured manuscripts, studied scrolls, and listened to the\n" +
            "greatest experts on the subjects that interest you. Your\n" +
            "efforts have made you a master in your fields of study.","When you attempt to learn or recall a piece of lore, if you\n" +
            "do not know that information, you often know where and\n" +
            "from whom you can obtain it. Usually, this information\n" +
            "comes from a library, scriptorium, university, or a sage\n" +
            "or other learned person or creature. Your DM might\n" +
            "rule that the knowledge you seek is secreted away in an\n" +
            "almost inaccessible place, or that it simply cannot be\n" +
            "found. Unearthing the deepest secrets of the multiverse\n" +
            "can require an adventure or even a whole campaign."),
    SAILOR("Sailor",Skills.ATHLETICS,Skills.PERCEPTION,"You sailed on a seagoing vessel for years. In that\n" +
            "time, you faced down mighty storms, monsters of the\n" +
            "deep, and those who wanted to sink your craft to the\n" +
            "bottomless depths. Your first love is the distant line of\n" +
            "the horizon, but the time has come to try your hand\n" +
            "at something new.\n" +
            "Discuss the nature of the ship you previously sailed\n" +
            "with your Dungeon Master. Was it a merchant ship,\n" +
            "a naval vessel, a ship of discovery, or a pirate ship?\n" +
            "How famous (or infamous) is it? Is it widely traveled?\n" +
            "Is it still sailing, or is it m issing and presumed lost\n" +
            "with all hands?\n" +
            "What were your duties on board—boatswain, captain,\n" +
            "navigator, cook, or some other position? Who were the\n" +
            "captain and first mate? Did you leave your ship on good\n" +
            "terms with your fellows, or on the run?","When you need to, you can secure free passage on\n" +
            "a sailing ship for yourself and your adventuring\n" +
            "companions. You might sail on the ship you served on,\n" +
            "or another ship you have good relations with (perhaps\n" +
            "one captained by a former crewmate). Because you are\n" +
            "calling in a favor, you can not be certain of a schedule or\n" +
            "route that will meet your every need. Your Dungeon\n" +
            "Master will determine how long it takes to get where\n" +
            "you need to go. In return for your free passage, you\n" +
            "and your companions are expected to assist the crew\n" +
            "during the voyage."),
    SOLDIER("Soldier",Skills.ATHLETICS,Skills.PERCEPTION,"War has been your life for as long as you care to\n" +
            "remember. You trained as a youth, studied the use of\n" +
            "weapons and armor, learned basic survival techniques,\n" +
            "including how to stay alive on the battlefield. You\n" +
            "might have been part of a standing national army or a\n" +
            "mercenary company, or perhaps a member of a local\n" +
            "militia who rose to prominence during a recent war.\n" +
            "When you choose this background, work with your\n" +
            "DM to determine which military organization you w ere\n" +
            "a part of, how far through its ranks you progressed, and\n" +
            "what kind of experiences you had during your military\n" +
            "career. Was it a standing army, a town guard, or a village\n" +
            "militia? Or it might have been a noble’s or merchant’s\n" +
            "private army, or a mercenary company.","MILITARY RANK: You have a military rank from your career as a soldier.\n" +
            "Soldiers loyal to your former military organization\n" +
            "still recognize your authority and influence, and they\n" +
            "defer to you if they are of a lower rank. You can invoke\n" +
            "your rank to exert influence over other soldiers and\n" +
            "requisition simple equipment or horses for temporary\n" +
            "use. You can also usually gain access to friendly\n" +
            "military encampments and fortresses where your\n" +
            "rank is recognized."),
    URCHIN("Urchin",Skills.SLEIGHTOFHAND,Skills.STEALTH,"You grew up on the streets alone, orphaned, and poor.\n" +
            "You had no one to watch over you or to provide for\n" +
            "you, so you learned to provide for yourself. You fought\n" +
            "fiercely over food and kept a constant watch out for other\n" +
            "desperate souls who might steal from you. You slept on\n" +
            "rooftops and in alleyways, exposed to the elements, and\n" +
            "endured sickness without the advantage o f medicine or\n" +
            "a place to recuperate. You’ve survived despite all odds,\n" +
            "and did so through cunning, strength, speed, or some\n" +
            "combination of each.\n" +
            "You begin your adventuring career with enough\n" +
            "money to live modestly but securely for at least ten days.\n" +
            "How did you come by that money? What allowed you to\n" +
            "break free of your desperate circumstances and embark\n" +
            "on a better life?","CITY SECRETS: You know the secret patterns and flow to cities and can\n" +
            "find passages through the urban sprawl that others would\n" +
            "miss. When you are not in combat, you (and companions\n" +
            "you lead) can travel between any two locations in the city\n" +
            "twice as fast as your speed would normally allow.");



    public final String viewName;
    public final Skills proficiency1;
    public final Skills proficiency2;
    public final String description;
    public final String feature;


    CharacterBackground(String viewName, Skills proficiency1, Skills proficency2,String description, String feature){
        this.viewName =viewName;
        this.proficiency1 =proficiency1;
        this.proficiency2=proficency2;
        this.description =description;
        this.feature = feature;
    }
}
