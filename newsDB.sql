
drop table newsDB purge;


create table newsDB(
NewsTitle varchar2(500),
day varchar2(500),
contents varchar2(3000),
url		varchar2(1000)
);

select * from NEWSDB;
delete from newsDB;

create table newsDB2 as
    select title, day, contents, url, rownum as newsid
    from newsDB;

    

select rownum as newsid, Title || ' ' || Contents as total_contents 
    from newsDB ;
    
    
    
select rownum as newsid, regexp_substr(Title || ' ' || Contents, '[^ ]+', 1, level) as sliced_words
	from newsDB
	connect by level <= regexp_count(Title || ' ' || Content, '[^ ]+')
		and prior rownum = newsid
		and prior dbms_random.value is not null;
	
 
		
 
-- 성공!
 select rownum as newsid, regexp_substr(Title || ' ' || Contents, '[^ ]+', 1, level) as sliced_words, url
    from newsDB
    connect by level <= regexp_count(Title || ' ' || Contents, '[^ ]+')
--        and prior newsid = newsid 
        and prior dbms_random.value is not null;  
--성공!-----------------------------------------------------
    

        
        -- 오리지널
        select rownum as newsid, NewsTitle,--regexp_substr(Title || ' ' || Contents, '[^ ]+', 1, level) as sliced_words, 
       		
            case when substr(regexp_substr(Title || ' ' || Contents, '[^ ]+', 1, level), -1) in ('은', '는', '이', '가', '와', '의', '에' ) 
            		then rtrim(regexp_substr(Title || ' ' || Contents, '[^ ]+', 1, level), substr(regexp_substr(Title || ' ' || Contents, '[^ ]+', 1, level),-1))
                     when substr(regexp_substr(Title || ' ' || Contents, '[^ ]+', 1, level), -2) in ('에서', '에게', '부터', '까지', '같이') 
                     then rtrim(regexp_substr(Title || ' ' || Contents, '[^ ]+', 1, level),substr(regexp_substr(Title || ' ' || Contents, '[^ ]+', 1, level),-2))
                     else regexp_substr(Title || ' ' || Contents, '[^ ]+', 1, level)
                     
            end as sliced_words2
             from newsDB
            
            connect by level <= regexp_count(Title || ' ' || Contents, '[^ ]+')
            and prior dbms_random.value is not null;
        
        -- 오리지널의 끝 ㅜㅜ 이것은 된다
        
        

        
        
    


        select rownum as newsid,
       		
            case when substr(regexp_substr(Title || ' ' || Contents, '[^ ]+', 1, level), -1) in ('은', '는', '이', '가', '와', '의', '에' ) 
            		then rtrim(regexp_substr(Title || ' ' || Contents, '[^ ]+', 1, level), substr(regexp_substr(Title || ' ' || Contents, '[^ ]+', 1, level),-1))
                     when substr(regexp_substr(Title || ' ' || Contents, '[^ ]+', 1, level), -2) in ('에서', '에게', '부터', '까지', '같이') 
                     then rtrim(regexp_substr(Title || ' ' || Contents, '[^ ]+', 1, level),substr(regexp_substr(Title || ' ' || Contents, '[^ ]+', 1, level),-2))
                     else regexp_substr(Title || ' ' || Contents, '[^ ]+', 1, level)
                     
            end as sliced_words2, 
            
            count(case when substr(regexp_substr(Title || ' ' || Contents, '[^ ]+', 1, level), -1) in ('은', '는', '이', '가', '와', '의', '에' ) 
            		then rtrim(regexp_substr(Title || ' ' || Contents, '[^ ]+', 1, level), substr(regexp_substr(Title || ' ' || Contents, '[^ ]+', 1, level),-1))
                     when substr(regexp_substr(Title || ' ' || Contents, '[^ ]+', 1, level), -2) in ('에서', '에게', '부터', '까지', '같이') 
                     then rtrim(regexp_substr(Title || ' ' || Contents, '[^ ]+', 1, level),substr(regexp_substr(Title || ' ' || Contents, '[^ ]+', 1, level),-2))
                     else regexp_substr(Title || ' ' || Contents, '[^ ]+', 1, level)) as word_cnt
            
                from newsDB
  

            
            connect by level <= regexp_count(Title || ' ' || Contents, '[^ ]+')
            and prior dbms_random.value is not null
            order by newsid, word_cnt desc
    		where word_cnt > 1;    
 
--------------------------------------------------------------------------------------------------------------------------
      
           
    select newsid, sliced_words2, count(sliced_words2) as word_cnt
    from t2_word
    group by newsid, sliced_words2
    order by newsid, word_cnt desc
    where word_cnt > 1;                
   
      
-- 민정님의 코드 ---------------------------------------
    
    select B.NewsTitle, B.sliced_words, count(B.sliced_words) as word_cnt
            from 
            (select NewsTitle, 
                        case when substr(regexp_substr(NewsTitle || ' ' || Contents, '[^ ]+', 1, level), -1) in ('은', '는', '이', '가', '와', '의', '에', '을', '를') then rtrim(regexp_substr(NewsTitle || ' ' || Contents, '[^ ]+', 1, level), substr(regexp_substr(NewsTitle || ' ' || Contents, '[^ ]+', 1, level),-1))
                                 when substr(regexp_substr(NewsTitle || ' ' || Contents, '[^ ]+', 1, level), -2) in ('에서', '에게', '부터', '까지', '같이') then rtrim(regexp_substr(NewsTitle || ' ' || Contents, '[^ ]+', 1, level),substr(regexp_substr(NewsTitle || ' ' || Contents, '[^ ]+', 1, level),-2))
                                 else regexp_substr(NewsTitle || ' ' || Contents, '[^ ]+', 1, level)                     
                        end as sliced_words , day
                        from newsDB
                                 connect by level <= regexp_count(NewsTitle || ' ' || Contents, '[^ ]+')
                                 and prior NewsTitle = NewsTitle
                                 and prior dbms_random.value is not null) B
            group by B.NewsTitle, B.sliced_words, day
            order by B.NewsTitle, word_cnt desc;

    
    
    
    
    
    
    
    
    
            
            
        
select * from newsDB;
delete from newsDB;




--=====================================================================--
--new challenger!!





 select title,           
            case when substr(regexp_substr(title || ' ' || Contents, '[^ ]+', 1, level), -1) in ('은', '는', '이', '가', '와', '의', '에' ) then rtrim(regexp_substr(title || ' ' || Contents, '[^ ]+', 1, level), substr(regexp_substr(title || ' ' || Contents, '[^ ]+', 1, level),-1))
                     when substr(regexp_substr(title || ' ' || Contents, '[^ ]+', 1, level), -2) in ('에서', '에게', '부터', '까지', '같이') then rtrim(regexp_substr(title || ' ' || Contents, '[^ ]+', 1, level),substr(regexp_substr(title || ' ' || Contents, '[^ ]+', 1, level),-2))
                     else regexp_substr(title || ' ' || Contents, '[^ ]+', 1, level)                     
            end as sliced_words2 /*, count(sliced_words2) as word_cnt*/
            from newsDB
            connect by level <= regexp_count(title || ' ' || Contents, '[^ ]+')
            and prior dbms_random.value is not null
            group by title, sliced_words2
            order by title, word_cnt desc;