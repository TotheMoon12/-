#include <iostream>
using namespace std;
enum
{
	MOP = 1,
	HEAL
};

int main(void)
{
	int roomNum;
	long long power;
	long long maxHP;
	long long currentHP;
	long long mopPower;
	long long mopHP;
	int state;
	long long healAmount = 0;
	long long powerUp;
	long long cumulativeDamage = 0;
	bool bHeal = false;
	bool bcumDamageBigthanDamage = false;
	long long preDamage = 0;
	bool bFirst = true;

	cin >> roomNum;
	cin >> power;

	for (int i = 0; i < roomNum; ++i)
	{
		cin >> state;

		if (state == MOP)
		{
			cin >> mopPower;
			cin >> mopHP;
			long long damage;

			if (mopHP % power == 0)
			{
				damage = ((long long)(mopHP / power) - 1) * (long long)mopPower;
			}
			else
			{
				damage = (mopHP / power) * (long long)mopPower;
			}

			if (bFirst)
			{
				maxHP = damage + 1;
				currentHP = maxHP;
				bFirst = false;
			}

			if (!bHeal) // 이전 방이 힐방이 아닌 경우
			{
				if (currentHP - damage < 1) // 현재체력에서 데미지 받아서 사망하는 경우 최대체력이 부족한 체력만큼 증가해야한다.
				{
					maxHP += 1 - currentHP + damage;
					currentHP = 1;
				}
				else
				{
					currentHP -= damage;
				}
			}
			else // 이전 방이 힐방이였는 경우
			{
				if (currentHP + healAmount > maxHP)
				{
					currentHP = maxHP;
				}
				else
				{
					currentHP += healAmount;
				}

				if (currentHP - damage < 1)
				{
					maxHP += 1 + damage - currentHP;
					currentHP = 1;
				}
				else
				{
					currentHP = currentHP - damage;
				}

				bHeal = false;
				healAmount = 0;
			}
		}
		else
		{
			long long AddingHeal;
			cin >> powerUp;
			cin >> AddingHeal;
			healAmount += AddingHeal;

			bHeal = true;
			power += powerUp;
		}
	}

	cout << maxHP;
	return 0;
}
