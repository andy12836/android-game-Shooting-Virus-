# android-game-Shooting-Virus-
App 名稱為 Shooting Virus，主要內容為⼀個簡單的⼩遊戲  
⾸⾴內容包含畫⾯正中央的 Start，點擊即可切換到遊戲畫⾯(Activity切換)  
左上⻆為 About，包含作者內容以及使⽤到的圖⽚素材  
正上⽅為 High Score，顯⽰過往遊玩紀錄的⾼分榜  
右上⽅為 Settings，包含遊戲設定以及⼀個發送通知的按鈕  
  
<img src="https://github.com/user-attachments/assets/5c6d4c8d-173d-4774-9ad7-cdf40ee6fa2a" width="15%"></img>

點擊⾸⾴的 "Start"，可以進到遊戲畫⾯。遊戲⽅式為點擊下⽅的三個注射器  
若點擊的注射器為病毒所在的欄位，則可以消除病毒，獲得1分。  
此時畫⾯會更新，使所有病毒往下降，並在最上⽅隨機⽣成⼀個新病毒  
(如下圖⽰例則須點擊最左⽅的注射器，就可以消除病毒，得到分數)  
<img src="https://github.com/user-attachments/assets/695cee06-5735-4401-8819-dc543bd29ff2" width="15%"></img>

High Score ⾴⾯會顯⽰過往遊戲的⾼分紀錄，最多顯⽰⼗筆資料  
圖中的⾴⾯顯⽰的紀錄為遊玩時間30秒的⾼分紀錄，點擊右⽅的 "60" ⽂字可以切換到  
遊玩時間60秒的⾼分紀錄 (使⽤navigation, Recycler View)  

<img src="https://github.com/user-attachments/assets/84affb4b-eacd-4e7d-886e-db7a450db2cc" width="15%"></img>

