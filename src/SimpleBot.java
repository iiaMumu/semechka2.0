import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;;


    class SimpleBot {
        final String[] COMMON_PHRASES={
                "Приятно когда текст без орфографических ошибок",
                "Я тебя не понимаю,ты что-то не то написал"};
        final String[] ELUSIVE_ANSWERS = {
                "Вопрос непростой, прошу тайм-аут на раздумья",
                "Не уверен, что раполагаю такой информацией",
                "Может лучше поговорим о чем-то другом?",
                "Простите, но это очень личный вопрос",
                "Не уверен, что Вам понравится ответ",
                "Поверьте, я сам бы хотел это знать",
                "Вы действительно хотите знать?",
                "Уверен, Вы уже догадались сами",
                "Зачем Вам такая инфрмация?",
                "Давайте сохарним интригу?"};
        final Map<String, String> PATTERNS_FOR_ANALYSIS = new HashMap<String,String>() {
            {
              //hello , hi
                put("хай", "hello");
                put("привет", "hello");
                put("здорово", "hello");
                put("здравствуй", "hello");
                //who
                put("кто\\s.*ты", "who");
                put("ты\\s.*кто", "who");
                //name
                put("как\\s.*зовут", "name");
                put("как\\s.*имя", "name");
                put("есть\\s.*имя", "name");
                put("какое\\s.*имя", "name");
                //wannaTalk
                put("новости","news");
                put("поговорить","news");
                //howareyou
                put("как\\s.*дела", "howareyou");
                put("как\\s.*;жизнь", "howareyou");
                //whatdoyoudoing
                put("зачем\\s.*тут", "whatdoyoudoing");
                put("зачем\\s.*здесь", "whatdoyoudoing");
                put("что\\s.*делаешь", "whatdoyoudoing");
                put("чем\\s.*занимаешься", "whatdoyoudoing");
                //whatdoyoulike
                put("что\\s.*нравится", "whatdoyoulike");
                put("что\\s.*любишь", "whatdoyoulike");
                //iamfeeling
                put("кажется", "iamfeeling");
                put("чувствую", "iamfeeling");
                put("испытываю", "iamfeeling");
                //yes
                put("да", "yes");
                put("согласен", "yes");
                //iCant
                put("не могу","cant");
                put("не смогу","cant");
                put("не уверен","cant");
                put("не уверена","cant");
                put("не получается","cant");
                //otvetOK
                put("нормально","iamOk");
                put("хорошо","iamOk");
                put("замечательно","iamOk");
                put("норм","iamOk");
                //otvetBad
                put("плохо","bad");
                put("ужасно","bad");
                put("не очень","bad");
                //nervous
                put("нервничаю","nervous");
                //thanks
                put("спасибо","thanks");
                put("помог"," thanks");
                put("благодарю","thanks");
                //whattime
                put("который\\s.*час", "whattime");
                put("сколько\\s.*времени", "whattime");
                //bye
                put("пока", "bye");
                put("увидимся", "bye");
                put("до\\s.*свидания", "bye");
                //commands
                put("Вот мой список комманд :" +
                        "привет" +
                        "как имя" +
                        "как дела" +
                        "что делаешь" +
                        "что любишь" +
                        "я чувствую" +
                        "который час" +
                        "пока","commands");
            }
        };
        final Map<String,String> ANSWERS_BY_PATTERNS = new HashMap<String,String>(){{
            put("hello"," И снова здравствуй с:");
            put("who", "Я бот психолог");
            put("name", "Меня зовут Семечка или же Семен,как тебе удобнее ");
            put("howareyou", "У меня все хорошо,меня больше волнует ,как у тебя дела?");
            put("whatdoyoudoing", "Я помогаю людям,когда они ко мне обращаются");
            put("whatdoyoulike", "Мне нравится думать, что я не просто программа");
            put("iamfeeling", "Как давно это началось? Расскажите чуть подробнее");
            put("yes", "Именно");
            put("bye", "До встречи! :)");
            put("iamOK","Рад это слышать!♥");
            put("bad","Расскажи мне,я попробую помочь ");
            put("nervous","Расскажи мне :)");
            put("cant","Даже если ты споткнулся и упал, ты все равно продвинулся вперед, помни об этом!\n" +
                    "Ты все сможешь и рано опускаешь руки ." +
                    "Я не могу пережить это вместо тебя, я всего-лишь бот. Но я могу прожить это вместе с тобой. А вместе мы все сможем ♥\n" );
            put("","");
            put("","");
            put("","");
            put("","");
            put("","");
            put("","");
            put("","");
            put("","");
            put("","");

        }};

        Pattern pattern;
        Random random;
        Date date;

        SimpleBot(){
            random = new Random();
            date = new Date();
        }

        String sayInReturn(String msg, boolean ai){
            String say = (msg.trim().endsWith("?"))?
                    ELUSIVE_ANSWERS[random.nextInt(ELUSIVE_ANSWERS.length)]:
                    COMMON_PHRASES[random.nextInt(COMMON_PHRASES.length)];
            if(ai){
                String message = String.join(" ",msg.toLowerCase().split("[ {, |.}?]+"));
                for (Map.Entry<String,String> o: PATTERNS_FOR_ANALYSIS.entrySet()){
                    pattern=Pattern.compile(o.getKey());
                    if(pattern.matcher(message).find())
                        if (o.getValue().equals("whattime")) return date.toString();
                        else return ANSWERS_BY_PATTERNS.get(o.getValue());
                }
            }
            return say;
        }
    }
